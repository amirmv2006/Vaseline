package ir.amv.os.vaseline.ws.rest.secured.oauth2.osgi.authorization;

import java.security.Principal;

/**
 * @author Amir
 */
public interface IRestPartialAuthorizationHandler {

    boolean isUserInRole(Principal user, String role );
}
