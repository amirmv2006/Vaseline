package ir.amv.os.vaseline.security.apis.authentication.basicimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;

/**
 * @author Amir
 */
public interface IImplementedThreadLocalAuthenticationApi
        extends IAuthenticationApi, ISetAuthenticationApi {

    ThreadLocal<String> getCurrentUsernameThreadLocal();

    @Override
    default String getCurrentUsername() throws BaseVaselineServerException {
        return getCurrentUsernameThreadLocal().get();
    }

    @Override
    default void setCurrentUsername(final String username) {
        notify(listener -> listener.currentUserChanged(username));
        getCurrentUsernameThreadLocal().set(username);
    }

}
