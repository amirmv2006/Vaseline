package ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.role;

import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.business.role.ISecurityRoleApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.role.ISecurityRoleDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role.ISecurityRole;

/**
 * @author Amir
 */
public interface IImplementedSecurityRoleApi<R extends ISecurityRole<?>, DAO extends ISecurityRoleDao<R>>
        extends IBaseImplementedReadOnlyApi<R, Long, DAO>,
        ISecurityRoleApi<R> {
}
