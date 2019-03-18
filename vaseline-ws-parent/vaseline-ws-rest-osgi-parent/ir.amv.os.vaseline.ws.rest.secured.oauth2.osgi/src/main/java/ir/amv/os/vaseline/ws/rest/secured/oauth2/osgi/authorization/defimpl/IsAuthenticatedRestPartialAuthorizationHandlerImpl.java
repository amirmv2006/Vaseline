package ir.amv.os.vaseline.ws.rest.secured.oauth2.osgi.authorization.defimpl;

import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;
import ir.amv.os.vaseline.ws.rest.secured.oauth2.osgi.authorization.IRestPartialAuthorizationHandler;
import org.osgi.service.component.annotations.Component;

import java.security.Principal;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IRestPartialAuthorizationHandler.class
)
public class IsAuthenticatedRestPartialAuthorizationHandlerImpl
        implements IRestPartialAuthorizationHandler {
    @Override
    public boolean isUserInRole(final Principal user, final String role) {
        return user != null && user.getName() != null && role.equals(IAuthenticationApi.IS_AUTHENTICATED);
    }
}
