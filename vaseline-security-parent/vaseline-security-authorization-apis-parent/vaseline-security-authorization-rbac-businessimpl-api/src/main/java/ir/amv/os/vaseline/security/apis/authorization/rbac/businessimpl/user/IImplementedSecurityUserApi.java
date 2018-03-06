package ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.user;

import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authentication.businessimpl.server.base.IImplementedBaseUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.business.user.ISecurityUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user.ISecurityUserWithRoleEntity;

/**
 * @author Amir
 */
public interface IImplementedSecurityUserApi<U extends ISecurityUserWithRoleEntity<?>, Dao extends ISecurityUserDao<U>>
        extends IBaseImplementedReadOnlyApi<U, Long, Dao>, IImplementedBaseUserApi<U, Dao>, ISecurityUserApi<U> {

}
