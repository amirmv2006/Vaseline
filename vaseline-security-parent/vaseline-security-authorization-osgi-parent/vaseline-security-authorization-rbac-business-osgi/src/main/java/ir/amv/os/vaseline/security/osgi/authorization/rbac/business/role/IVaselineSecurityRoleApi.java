package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.role;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.business.layer.IBaseNotSecuredCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.business.role.ISecurityRoleApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role.SecurityRoleEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityRoleApi
        extends
        ISecurityRoleApi<SecurityRoleEntity>,
        IBaseCrudApi<SecurityRoleEntity, Long>,
        IBaseNotSecuredCrudApi<SecurityRoleEntity, Long> {
    SecurityRoleEntity getRoleByName(String roleName) throws BaseVaselineServerException;
}
