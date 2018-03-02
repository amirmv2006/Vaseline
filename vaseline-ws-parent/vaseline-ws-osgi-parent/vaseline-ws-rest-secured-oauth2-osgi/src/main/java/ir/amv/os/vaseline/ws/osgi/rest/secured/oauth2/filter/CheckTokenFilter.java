package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.filter;

import com.eclipsesource.jaxrs.provider.security.AuthenticationHandler;
import com.eclipsesource.jaxrs.provider.security.AuthorizationHandler;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.ISetAuthenticationApi;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.IOAuthService;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.OAuthToken;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.security.Principal;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                AuthenticationHandler.class,
                AuthorizationHandler.class
        }
)
public class CheckTokenFilter implements AuthenticationHandler, AuthorizationHandler {

    private IOAuthService oAuthService;
    private ISetAuthenticationApi setAuthenticationApi;

    @Override
    public Principal authenticate(final ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            OAuthToken oAuthToken = oAuthService.validateToken(token, null);
            setAuthenticationApi.setCurrentUsername(oAuthToken.getUser_name());
            return oAuthToken::getUser_name;
        }
        return null;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }

    @Reference
    public void setoAuthService(final IOAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @Reference
    public void setSetAuthenticationApi(final ISetAuthenticationApi setAuthenticationApi) {
        this.setAuthenticationApi = setAuthenticationApi;
    }

    @Override
    public boolean isUserInRole(final Principal user, final String role) {
        return user != null && user.getName() != null && role.equals(IAuthenticationApi.IS_AUTHENTICATED);
    }
}
