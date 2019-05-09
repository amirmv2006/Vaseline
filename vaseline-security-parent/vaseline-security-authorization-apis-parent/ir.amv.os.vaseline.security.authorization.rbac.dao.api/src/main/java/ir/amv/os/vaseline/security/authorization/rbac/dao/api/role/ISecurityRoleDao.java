package ir.amv.os.vaseline.security.authorization.rbac.dao.api.role;

import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

/**
 * @author Amir
 */
public interface ISecurityRoleDao<R extends ISecurityRole<?>>
        extends IBaseReadOnlyDao<Long, R> {
}
