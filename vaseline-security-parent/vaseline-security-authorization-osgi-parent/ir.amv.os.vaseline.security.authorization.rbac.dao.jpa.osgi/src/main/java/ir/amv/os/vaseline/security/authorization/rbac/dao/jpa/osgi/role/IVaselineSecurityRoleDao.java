package ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.role;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.role.SecurityRoleEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityRoleDao
        extends ISecurityRoleDao<SecurityRoleEntity>, IBaseCrudDao<SecurityRoleEntity, Long> {
    SecurityRoleEntity getByRoleName(String roleName);
}
