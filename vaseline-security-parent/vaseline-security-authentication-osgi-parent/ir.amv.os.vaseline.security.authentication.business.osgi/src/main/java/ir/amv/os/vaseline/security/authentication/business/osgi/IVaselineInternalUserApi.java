package ir.amv.os.vaseline.security.authentication.business.osgi;

import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.authentication.business.api.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.authentication.model.def.server.base.VaselineInternalUserEntity;

/**
 * @author Amir
 */
public interface IVaselineInternalUserApi
        extends IBaseUserApi<VaselineInternalUserEntity>, IBaseCrudApi<VaselineInternalUserEntity, Long> {
}
