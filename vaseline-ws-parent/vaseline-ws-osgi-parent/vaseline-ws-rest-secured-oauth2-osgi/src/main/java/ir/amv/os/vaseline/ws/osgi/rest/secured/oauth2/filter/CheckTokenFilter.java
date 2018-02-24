package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.filter;

import com.eclipsesource.jaxrs.provider.security.AuthenticationHandler;
import com.eclipsesource.jaxrs.provider.security.AuthorizationHandler;
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

    @Override
    public Principal authenticate(final ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null) {
            String token = authorizationHeader.substring("Bearer ".length());
            OAuthToken oAuthToken = oAuthService.validateToken(token, null);
            return () -> oAuthToken.getUser_name();
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

    @Override
    public boolean isUserInRole(final Principal user, final String role) {
        return true;
    }
}
