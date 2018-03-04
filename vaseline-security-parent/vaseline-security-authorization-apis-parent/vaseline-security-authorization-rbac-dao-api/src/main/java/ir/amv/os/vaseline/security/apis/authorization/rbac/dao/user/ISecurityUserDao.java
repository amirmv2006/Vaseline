package ir.amv.os.vaseline.security.apis.authorization.rbac.dao.user;

import ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user.ISecurityUserWithRoleEntity;

import java.util.List;

/**
 * @author Amir
 */
public interface ISecurityUserDao<U extends ISecurityUserWithRoleEntity<?>>
        extends IBaseUserDao<U> {

    List<String> getUserActionTreeNames(String username);

    List<String> getUsernamesWithAccessToActionTreeName(final String... actionTreeNames);
}
