package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.role;

import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role.SecurityRoleEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityRoleDao
        extends ISecurityRoleDao<SecurityRoleEntity>, IBaseCrudDao<SecurityRoleEntity, Long> {
    SecurityRoleEntity getByRoleName(String roleName);
}
