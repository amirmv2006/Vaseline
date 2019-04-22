package ir.amv.os.vaseline.security.authorization.rbac.business.def.role;

import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.role.ISecurityRoleApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IDefaultSecurityRoleApi<R extends ISecurityRole<?>, DAO extends ISecurityRoleDao<R>>
        extends IDefaultReadOnlyApi<R, Long, DAO>,
        ISecurityRoleApi<R> {
}
