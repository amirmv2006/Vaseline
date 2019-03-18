package ir.amv.os.vaseline.security.authorization.rbac.business.def.role;

import ir.amv.os.vaseline.business.basic.def.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.role.ISecurityRoleApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IImplementedSecurityRoleApi<R extends ISecurityRole<?>, DAO extends ISecurityRoleDao<R>>
        extends IBaseImplementedReadOnlyApi<R, Long, DAO>,
        ISecurityRoleApi<R> {
}
