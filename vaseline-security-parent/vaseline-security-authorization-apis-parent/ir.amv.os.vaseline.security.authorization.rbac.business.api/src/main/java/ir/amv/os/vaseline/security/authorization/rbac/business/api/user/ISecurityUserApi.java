package ir.amv.os.vaseline.security.authorization.rbac.business.api.user;

import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.authentication.business.api.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleEntity;

/**
 * @author Amir
 */
public interface ISecurityUserApi<U extends ISecurityUserWithRoleEntity<?>>
        extends IBaseReadOnlyApi<Long, U>, IBaseUserApi<U> {
}
