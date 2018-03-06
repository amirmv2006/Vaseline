package ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.jpa.role;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseImplementedJpaReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IImplementedSecurityRoleJpaDao<R extends ISecurityRole<?>>
        extends IBaseImplementedJpaReadOnlyDao<R, Long>, ISecurityRoleDao<R> {
}
