package ir.amv.os.vaseline.security.apis.authentication.business.server.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

/**
 * @author Amir
 */
public interface IBaseUserApi<U extends IBaseUserEntity>
        extends IBaseReadOnlyApi<U, Long> {

    U getUserByUsername(String username) throws BaseVaselineServerException;
}
