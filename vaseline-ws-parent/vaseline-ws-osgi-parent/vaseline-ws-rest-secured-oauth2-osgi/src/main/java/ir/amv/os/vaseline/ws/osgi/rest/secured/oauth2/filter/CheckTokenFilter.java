package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.filter;

import com.eclipsesource.jaxrs.provider.security.AuthenticationHandler;
import com.eclipsesource.jaxrs.provider.security.AuthorizationHandler;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.server.ISetAuthenticationApi;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.IOAuthService;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.OAuthToken;
import ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.authorization.IRestPartialAuthorizationHandler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    private List<IRestPartialAuthorizationHandler> partialAuthorizationHandlers = new ArrayList<>();

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


    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE,
            policyOption = ReferencePolicyOption.GREEDY
    )
    public void addPartialAuthorizationHandlers(final IRestPartialAuthorizationHandler
                                                      partialAuthorizationHandler) {
        partialAuthorizationHandlers.add(partialAuthorizationHandler);
    }

    public void removePartialAuthorizationHandlers(final IRestPartialAuthorizationHandler
                                                      partialAuthorizationHandler) {
        partialAuthorizationHandlers.remove(partialAuthorizationHandler);
    }

    @Override
    public boolean isUserInRole(final Principal user, final String role) {
        for (IRestPartialAuthorizationHandler partialAuthorizationHandler : partialAuthorizationHandlers) {
            if (partialAuthorizationHandler.isUserInRole(user, role)) {
                return true;
            }
        }
        return false;
    }
}
