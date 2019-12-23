package ir.amv.os.vaseline.security.authorization.rbac.business.def.user;

import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.security.authentication.business.def.server.base.IDefaultBaseUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.user.ISecurityUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleBusinessModel;

/**
 * @author Amir
 */
public interface IDefaultSecurityUserApi<U extends ISecurityUserWithRoleBusinessModel<?>, Dao extends ISecurityUserDao<U>>
        extends IDefaultReadOnlyApi<Long, U, Dao>, IDefaultBaseUserApi<U, Dao>, ISecurityUserApi<U> {

}
