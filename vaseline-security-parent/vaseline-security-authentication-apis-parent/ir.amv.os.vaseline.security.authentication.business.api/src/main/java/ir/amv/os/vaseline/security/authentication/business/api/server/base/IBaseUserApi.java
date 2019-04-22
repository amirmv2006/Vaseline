package ir.amv.os.vaseline.security.authentication.business.api.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserEntity;

/**
 * @author Amir
 */
public interface IBaseUserApi<U extends IBaseUserEntity>
        extends IBaseReadOnlyApi<U, Long> {

    /**
     * method used for authentication loading the user with the given username
     * @param username the username for the user
     * @return the user with the specified username
     * @throws BaseVaselineServerException if there are problems with loading the user
     */
    U loadUserByUsername(String username) throws BaseVaselineServerException;

    void authenticationSuccessful(String username) throws BaseVaselineServerException;

    void authenticationFailure(String username) throws BaseVaselineServerException;
}
