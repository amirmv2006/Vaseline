package ir.amv.os.vaseline.security.apis.authorization.rbac.dao.role;

import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role.ISecurityRole;

/**
 * @author Amir
 */
public interface ISecurityRoleDao<R extends ISecurityRole<?>>
        extends IBaseReadOnlyDao<R, Long> {
}
