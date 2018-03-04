package ir.amv.os.vaseline.security.apis.authorization.rbac.business.role;

import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role.ISecurityRole;

/**
 * @author Amir
 */
public interface ISecurityRoleApi<R extends ISecurityRole<?>>
        extends IBaseReadOnlyApi<R, Long>{
}
