package ir.amv.os.vaseline.security.authorization.rbac.dao.def.hibernate.role;

import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IDefaultSecurityRoleHibernateDao<R extends ISecurityRole<?>>
        extends IDefaultHibernateReadOnlyDao<R, Long>, ISecurityRoleDao<R> {
}
