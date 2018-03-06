package ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.hibernate.role;

import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IImplementedSecurityRoleHibernateDao<R extends ISecurityRole<?>>
        extends IBaseImplementedHibernateReadOnlyDao<R, Long>, ISecurityRoleDao<R> {
}
