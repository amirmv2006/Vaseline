package ir.amv.os.vaseline.security.authorization.basic.def.server;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.INoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultAuthorizationApi
        extends IAuthorizationApi {

    IAuthenticationApi getAuthenticationApi();
    INoAuthAuthorizationApi getNoAuthAuthorizationApi();

    @Override
    default void checkAuthorization(String actionTreeName) throws VaselineAuthorizationException {
        try {
            String currentUsername = getAuthenticationApi().getCurrentUsername();
            getNoAuthAuthorizationApi().checkAuthorization(currentUsername, actionTreeName);
        } catch (VaselineAuthorizationException e) {
            throw e;
        } catch (Exception e) {
            throw new VaselineAuthorizationException(e);
        }
    }

    @Override
    default List<String> getAuthorizedActionTreeNames() throws VaselineAuthorizationException {
        try {
            String currentUsername = getAuthenticationApi().getCurrentUsername();
            return getNoAuthAuthorizationApi().getAuthorizedActionTreeNames(currentUsername);
        } catch (VaselineAuthorizationException e) {
            throw e;
        } catch (BaseBusinessException e) {
            throw new VaselineAuthorizationException(e);
        }
    }
}
