package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.user;

import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.authorization.business.api.IBaseNotSecuredCrudApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.user.ISecurityUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.user.SecurityUserEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityUserApi
        extends
        ISecurityUserApi<SecurityUserEntity>,
        IBaseCrudApi<SecurityUserEntity, Long>,
        IBaseNotSecuredCrudApi<SecurityUserEntity, Long> {
}
