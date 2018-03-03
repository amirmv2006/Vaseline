package ir.amv.os.vaseline.security.apis.authorization.basic.server.api;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationUserApi extends IBaseApi {

    boolean hasUserAccessToAction(String username, String actionTreeName) throws VaselineAuthorizationException;

    List<String> getUserActionTreeNames(String currentUsername) throws VaselineAuthorizationException;

    List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName); // with access -> have this action or the parent or ...
}
