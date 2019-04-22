package ir.amv.os.vaseline.security.authentication.basic.def.server;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;

/**
 * @author Amir
 */
public interface IDefaultThreadLocalAuthenticationApi
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
