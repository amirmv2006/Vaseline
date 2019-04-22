package ir.amv.os.vaseline.security.authorization.rbac.dao.api.user;

import ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleEntity;

import java.util.List;

/**
 * @author Amir
 */
public interface ISecurityUserDao<U extends ISecurityUserWithRoleEntity<?>>
        extends IBaseUserDao<U> {

    List<String> getUserActionTreeNames(String username);

    List<String> getUsernamesWithAccessToActionTreeName(final String... actionTreeNames);
}
