package ir.amv.os.vaseline.security.authorization.rbac.business.api.user;

import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.authentication.business.api.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleBusinessModel;

/**
 * @author Amir
 */
public interface ISecurityUserApi<U extends ISecurityUserWithRoleBusinessModel<?>>
        extends IBaseReadOnlyApi<Long, U>, IBaseUserApi<U> {
}
