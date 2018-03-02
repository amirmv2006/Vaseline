package ir.amv.os.vaseline.security.apis.authorization.basic.server.api;

import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationUserApi {

    boolean hasUserAccessToAction(String username, String actionTreeName) throws VaselineAuthorizationException;

    List<String> getUserActionTreeNames(String currentUsername) throws VaselineAuthorizationException;

    List<String> getUsernamesWithAccessToActionTreeName(); // with access -> have this action or the parent or ...
}
