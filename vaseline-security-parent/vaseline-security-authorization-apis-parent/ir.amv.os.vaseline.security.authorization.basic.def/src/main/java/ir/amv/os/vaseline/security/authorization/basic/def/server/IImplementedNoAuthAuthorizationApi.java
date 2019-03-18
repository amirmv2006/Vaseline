package ir.amv.os.vaseline.security.authorization.basic.def.server;

import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.INoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAccessDeniedException;
import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedNoAuthAuthorizationApi
        extends INoAuthAuthorizationApi {
    IAuthorizationUserApi getAuthorizationUserApi();
    IAuthorizationActionApi getAuthorizationActionApi();

    @Override
    default void checkAuthorization(String username, String actionTreeName) throws VaselineAuthorizationException {
        try {
            if (!getAuthorizationUserApi().hasUserAccessToAction(username, actionTreeName)) {
                throw new VaselineAccessDeniedException(username, actionTreeName);
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
