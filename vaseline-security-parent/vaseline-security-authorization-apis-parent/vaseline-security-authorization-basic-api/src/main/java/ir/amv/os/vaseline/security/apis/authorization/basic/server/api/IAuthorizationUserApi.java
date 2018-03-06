package ir.amv.os.vaseline.security.apis.authorization.basic.server.api;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationUserApi {

    boolean hasUserAccessToAction(String username, String actionTreeName) throws BaseVaselineServerException;

    List<String> getUserActionTreeNames(String currentUsername) throws BaseVaselineServerException;

    List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName) throws BaseVaselineServerException; // with access -> have this action or the parent or ...
}
