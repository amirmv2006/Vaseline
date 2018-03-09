package ir.amv.os.vaseline.security.osgi.authentication.business;

import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.apis.authentication.business.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineInternalUserEntity;

/**
 * @author Amir
 */
public interface IVaselineInternalUserApi
        extends IBaseUserApi<VaselineInternalUserEntity>, IBaseCrudApi<VaselineInternalUserEntity, Long> {
}
