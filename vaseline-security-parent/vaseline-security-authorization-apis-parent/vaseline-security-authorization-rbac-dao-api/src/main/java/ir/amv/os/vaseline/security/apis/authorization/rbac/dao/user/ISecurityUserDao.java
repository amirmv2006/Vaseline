package ir.amv.os.vaseline.security.apis.authorization.rbac.dao.user;

import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user.ISecurityUserWithRoleEntity;

import java.util.List;

/**
 * @author Amir
 */
public interface ISecurityUserDao<U extends ISecurityUserWithRoleEntity<?>> {

    List<String> getUserActionTreeNames(String username);

    List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName);
}
