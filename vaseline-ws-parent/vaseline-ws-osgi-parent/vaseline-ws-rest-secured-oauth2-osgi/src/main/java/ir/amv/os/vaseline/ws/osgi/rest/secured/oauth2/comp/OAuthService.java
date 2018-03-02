package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.comp;

import ir.amv.os.vaseline.basics.apis.cache.server.IVaselineCacheApi;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.IOAuthService;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.OAuthToken;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.config.IVaselineOAuthConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.net.URI;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IOAuthService.class
)
public class OAuthService
        implements IOAuthService {

    private IVaselineOAuthConfig vaselineOAuthConfig;
    private IVaselineCacheApi cacheApi;
    private MessageBodyReader messageBodyReader;
    private MessageBodyWriter messageBodyWriter;

    @Context
    private UriInfo uriInfo;

    private Client client;

    @Activate
    public void init() {
        client = ClientBuilder.newClient();
        Configuration configuration = client.getConfiguration();
        client.register(messageBodyReader);
        client.register(messageBodyWriter);
    }

    @Override
    public Response login(final String username, final String password, final String scope) {
        final Form form = new Form();
        form.param(OAuth2Parameters.GrantType.key, OAuth2Parameters.GrantType.PASSWORD.name().toLowerCase());
        form.param("username", username);
        form.param("password", password);
        form.param(OAuth2Parameters.SCOPE, scope);
        form.param(OAuth2Parameters.STATE, UUID.randomUUID().toString());
        byte[] encoded = Base64.getEncoder().encode((vaselineOAuthConfig.getClientId() + ":" + vaselineOAuthConfig
                .getClientSecret()).getBytes());

        final Response response = client.target(vaselineOAuthConfig.getTokenUrl())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization","Basic " + new String(encoded))
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));


        if (response.getStatus() != 200) {
            throw new ProcessingException("Problem when logging in with status code: " + response.getStatus());
        }

        return response;
    }

    @Override
    public OAuthToken validateToken(String token, String state) {
        return cacheApi.cacheResult(IVaselineOAuthConfig.AUTHENTICATION_CACHE_GROUP + ":" + IVaselineOAuthConfig
                        .TOKEN_CACHE_NAME, token,
                (expiryPolicyConsumer) -> {
            final Form form = new Form();
            form.param("token", token);

            byte[] encoded = Base64.getEncoder().encode((vaselineOAuthConfig.getClientId() + ":" + vaselineOAuthConfig
                    .getClientSecret()).getBytes());

            final Response response = client.target(vaselineOAuthConfig.getCheckTokenUrl())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization","Basic " + new String(encoded))
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));



            if (response.getStatus() != 200) {
                throw new ProcessingException("Problem when validating token, with status code: " + response.getStatus
                        ());
            }
            OAuthToken oAuthToken = response.readEntity(OAuthToken.class);
            Duration expiryDuration = new Duration(System.currentTimeMillis(), oAuthToken.getExp());
            expiryPolicyConsumer.accept(new CreatedExpiryPolicy(expiryDuration));
            return oAuthToken;
        });
    }

    @Override
    public Response authCodeInit(final String scope) {
        String state = UUID.randomUUID().toString();
        Duration expiryDuration = new Duration(System.currentTimeMillis(),
                System.currentTimeMillis() + 60_000);
        cacheApi.cachePut(IVaselineOAuthConfig.AUTHENTICATION_CACHE_GROUP + ":" + IVaselineOAuthConfig
                .STATE_CACHE_NAME, state, scope, new CreatedExpiryPolicy(expiryDuration));
        URI build = UriBuilder.fromUri(vaselineOAuthConfig.getAuthorizeUrl())
                .queryParam(OAuth2Parameters.SCOPE, scope)
                .queryParam(OAuth2Parameters.STATE, state)
                .queryParam(OAuth2Parameters.CLIENT_ID, vaselineOAuthConfig.getClientId())
                .queryParam(OAuth2Parameters.CLIENT_SECRET, vaselineOAuthConfig.getClientSecret())
                .queryParam(OAuth2Parameters.REDIRECT_URI, getRedirectUri())
                .queryParam("response_type", "code")
                .build();
        return Response.seeOther(build).build();
    }

    @Override
    public OAuthToken authCodeCallback(final String authCode, final String state) {
        String scope = cacheApi.cacheGet(IVaselineOAuthConfig.AUTHENTICATION_CACHE_GROUP + ":" +
                IVaselineOAuthConfig.STATE_CACHE_NAME, state);
        String redirectUrl = getRedirectUri();
        final Form form = new Form();
        form.param(OAuth2Parameters.GrantType.key, OAuth2Parameters.GrantType.AUTHORIZATION_CODE.name().toLowerCase());
        form.param(OAuth2Parameters.SCOPE, scope);
        form.param(OAuth2Parameters.CODE, authCode);
        form.param(OAuth2Parameters.REDIRECT_URI, redirectUrl);
        byte[] encoded = Base64.getEncoder().encode((vaselineOAuthConfig.getClientId() + ":" + vaselineOAuthConfig
                .getClientSecret()).getBytes());

        final Response response = client.target(vaselineOAuthConfig.getTokenUrl())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization","Basic " + new String(encoded))
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));


        if (response.getStatus() != 200) {
            throw new ProcessingException("Problem when logging in with status code: " + response.getStatus());
        }

        OAuthToken oAuthToken = response.readEntity(OAuthToken.class);
        return oAuthToken;
    }

    private String getRedirectUri() {
        try {
            return UriBuilder.fromUri(uriInfo.getBaseUri()).path(
                    UriBuilder.fromResource(IOAuthService.class).path(
                            IOAuthService.class.getMethod("authCodeCallback", String.class, String.class)
                    ).build().toString()
            ).build().toString();
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    @Reference
    public void setVaselineOAuthConfig(final IVaselineOAuthConfig vaselineOAuthConfig) {
        this.vaselineOAuthConfig = vaselineOAuthConfig;
    }

    @Reference
    public void setCacheApi(final IVaselineCacheApi cacheApi) {
        this.cacheApi = cacheApi;
    }

    @Reference
    public void setMessageBodyReader(final MessageBodyReader messageBodyReader) {
        this.messageBodyReader = messageBodyReader;
    }

    @Reference
    public void setMessageBodyWriter(final MessageBodyWriter messageBodyWriter) {
        this.messageBodyWriter = messageBodyWriter;
    }
}
