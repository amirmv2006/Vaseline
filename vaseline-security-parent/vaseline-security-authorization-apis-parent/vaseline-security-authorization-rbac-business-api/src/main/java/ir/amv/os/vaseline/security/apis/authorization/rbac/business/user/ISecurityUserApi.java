package ir.amv.os.vaseline.security.apis.authorization.rbac.business.user;

import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authentication.business.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user.ISecurityUserWithRoleEntity;

/**
 * @author Amir
 */
public interface ISecurityUserApi<U extends ISecurityUserWithRoleEntity<?>>
        extends IBaseReadOnlyApi<U, Long>, IBaseUserApi<U>, IAuthorizationUserApi {
}
