package ir.amv.os.vaseline.security.apis.authorization.basicimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.base.IBaseImplementedApi;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.INoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedAuthorizationApi
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
        } catch (BaseVaselineServerException e) {
            throw new VaselineAuthorizationException(e);
        }
    }
}
