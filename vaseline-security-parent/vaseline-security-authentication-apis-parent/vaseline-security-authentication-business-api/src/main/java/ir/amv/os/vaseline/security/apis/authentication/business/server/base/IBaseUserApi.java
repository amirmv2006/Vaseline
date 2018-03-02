package ir.amv.os.vaseline.security.apis.authentication.business.server.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

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
