package ir.amv.os.vaseline.security.authorization.rbac.business.def.user;

import ir.amv.os.vaseline.business.basic.def.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.authentication.business.def.server.base.IImplementedBaseUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.user.ISecurityUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleEntity;

/**
 * @author Amir
 */
public interface IImplementedSecurityUserApi<U extends ISecurityUserWithRoleEntity<?>, Dao extends ISecurityUserDao<U>>
        extends IBaseImplementedReadOnlyApi<U, Long, Dao>, IImplementedBaseUserApi<U, Dao>, ISecurityUserApi<U> {

}
