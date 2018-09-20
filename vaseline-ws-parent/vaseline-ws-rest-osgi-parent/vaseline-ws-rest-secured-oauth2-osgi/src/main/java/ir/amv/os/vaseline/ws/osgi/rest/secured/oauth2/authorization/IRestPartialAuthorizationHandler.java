package ir.amv.os.vaseline.ws.osgi.rest.secured.oauth2.authorization;

import java.security.Principal;

/**
 * @author Amir
 */
public interface IRestPartialAuthorizationHandler {

    boolean isUserInRole(Principal user, String role );
}
