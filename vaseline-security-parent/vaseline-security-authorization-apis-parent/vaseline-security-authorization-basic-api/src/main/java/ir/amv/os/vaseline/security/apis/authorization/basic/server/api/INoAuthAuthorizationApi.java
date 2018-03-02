package ir.amv.os.vaseline.security.apis.authorization.basic.server.api;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface INoAuthAuthorizationApi extends IBaseApi {

    void checkAuthorization(String username, String actionTreeName) throws VaselineAuthorizationException;

    List<String> getAuthorizedActionTreeNames(String currentUsername) throws VaselineAuthorizationException;

    List<String> getChildTreeNamesOfAuthorizedAction(String baseActionTN) throws VaselineAuthorizationException;

    List<String> getUsernamesWithPermissionToAction(String actionTreeName) throws VaselineAuthorizationException;

}
