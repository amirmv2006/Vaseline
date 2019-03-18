package ir.amv.os.vaseline.security.authorization.basic.api.server.api;

import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface INoAuthAuthorizationApi {

    void checkAuthorization(String username, String actionTreeName) throws VaselineAuthorizationException;

    List<String> getAuthorizedActionTreeNames(String currentUsername) throws VaselineAuthorizationException;

    List<String> getChildTreeNamesOfAction(String baseActionTN) throws VaselineAuthorizationException;

    List<String> getUsernamesWithPermissionToAction(String actionTreeName) throws VaselineAuthorizationException;

}
