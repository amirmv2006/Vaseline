package ir.amv.os.vaseline.security.apis.authorization.basic.api;

import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.base.IBaseImplementedApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.INoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAccessDeniedException;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedNoAuthAuthorizationApi
        extends INoAuthAuthorizationApi, IBaseImplementedApi {
    IAuthorizationUserApi getAuthorizationUserApi();
    IAuthorizationActionApi getAuthorizationActionApi();

    @Override
    default void checkAuthorization(String username, String actionTreeName) throws VaselineAuthorizationException {
        try {
            if (!getAuthorizationUserApi().hasUserAccessToAction(username, actionTreeName)) {
                throw new VaselineAccessDeniedException();
            }
        } catch (VaselineAuthorizationException e) {
            throw e;
        } catch (Exception e) {
            throw new VaselineAuthorizationException(e);
        }
    }

    @Override
    default List<String> getAuthorizedActionTreeNames(String currentUsername) throws VaselineAuthorizationException {
        try {
            return getAuthorizationUserApi().getUserActionTreeNames(currentUsername);
        } catch (VaselineAuthorizationException e) {
            throw e;
        } catch (Exception e) {
            throw new VaselineAuthorizationException(e);
        }
    }

    @Override
    default List<String> getChildTreeNamesOfAction(String baseActionTN) {
        return getAuthorizationActionApi().getActionChildTreeNames(baseActionTN);
    }

    @Override
    default List<String> getUsernamesWithPermissionToAction(String actionTreeName) throws VaselineAuthorizationException {
        try {
            return getAuthorizationUserApi().getUsernamesWithAccessToActionTreeName(actionTreeName);
        } catch (VaselineAuthorizationException e) {
            throw e;
        } catch (Exception e) {
            throw new VaselineAuthorizationException(e);
        }
    }
}
