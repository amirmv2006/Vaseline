package ir.amv.os.vaseline.security.authorization.rbac.dao.def.jpa.role;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IBaseImplementedJpaReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IImplementedSecurityRoleJpaDao<R extends ISecurityRole<?>>
        extends IBaseImplementedJpaReadOnlyDao<R, Long>, ISecurityRoleDao<R> {
}
