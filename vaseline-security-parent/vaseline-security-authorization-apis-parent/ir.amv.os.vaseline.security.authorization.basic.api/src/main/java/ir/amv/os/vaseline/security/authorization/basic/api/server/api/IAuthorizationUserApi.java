package ir.amv.os.vaseline.security.authorization.basic.api.server.api;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationUserApi {

    boolean hasUserAccessToAction(String username, String actionTreeName) throws BaseBusinessException;

    List<String> getUserActionTreeNames(String currentUsername) throws BaseBusinessException;

    List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName) throws BaseBusinessException; // with access -> have this action or the parent or ...
}
