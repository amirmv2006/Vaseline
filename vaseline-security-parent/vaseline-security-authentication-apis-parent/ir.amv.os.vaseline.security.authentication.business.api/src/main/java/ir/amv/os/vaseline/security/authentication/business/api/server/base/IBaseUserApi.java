package ir.amv.os.vaseline.security.authentication.business.api.server.base;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserBusinessModel;

/**
 * @author Amir
 */
public interface IBaseUserApi<U extends IBaseUserBusinessModel>
        extends IBaseReadOnlyApi<Long, U> {

    /**
     * method used for authentication loading the user with the given username
     * @param username the username for the user
     * @return the user with the specified username
     * @throws BaseBusinessException if there are problems with loading the user
     */
    U loadUserByUsername(String username) throws BaseBusinessException;

    void authenticationSuccessful(String username) throws BaseBusinessException;

    void authenticationFailure(String username) throws BaseBusinessException;
}
