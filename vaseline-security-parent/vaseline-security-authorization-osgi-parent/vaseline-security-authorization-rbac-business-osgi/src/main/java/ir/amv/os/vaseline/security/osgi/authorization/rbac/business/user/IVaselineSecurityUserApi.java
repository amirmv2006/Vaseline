package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.user;

import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.business.layer.IBaseNotSecuredCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.business.user.ISecurityUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.user.SecurityUserEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityUserApi
        extends
        ISecurityUserApi<SecurityUserEntity>,
        IBaseCrudApi<SecurityUserEntity, Long>,
        IBaseNotSecuredCrudApi<SecurityUserEntity, Long> {
}
