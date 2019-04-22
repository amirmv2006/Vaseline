package ir.amv.os.vaseline.security.authorization.basic.api.server.api;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationUserApi {

    boolean hasUserAccessToAction(String username, String actionTreeName) throws BaseVaselineServerException;

    List<String> getUserActionTreeNames(String currentUsername) throws BaseVaselineServerException;

    List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName) throws BaseVaselineServerException; // with access -> have this action or the parent or ...
}
