package ir.amv.os.vaseline.security.authorization.rbac.business.api.role;

import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

/**
 * @author Amir
 */
public interface ISecurityRoleApi<R extends ISecurityRole<?>>
        extends IBaseReadOnlyApi<R, Long>{
}
