package ir.amv.os.vaseline.security.authorization.rbac.dao.def.jpa.role;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyRepository;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IDefaultSecurityRoleJpaDao<R extends ISecurityRole<?>>
        extends IDefaultJpaReadOnlyRepository<Long, R>, ISecurityRoleDao<R> {
}
