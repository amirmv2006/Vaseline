package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.role;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.authorization.business.api.IBaseNotSecuredCrudApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.role.ISecurityRoleApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.role.SecurityRoleEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityRoleApi
        extends
        ISecurityRoleApi<SecurityRoleEntity>,
        IBaseCrudApi<Long, SecurityRoleEntity>,
        IBaseNotSecuredCrudApi<SecurityRoleEntity, Long> {
    SecurityRoleEntity getRoleByName(String roleName) throws BaseVaselineServerException;
}
