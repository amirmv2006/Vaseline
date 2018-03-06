package ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user;

import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role.ISecurityRole;

import java.util.Set;

/**
 * @author Amir
 */
public interface ISecurityUserWithRoleEntity<R extends ISecurityRole>
        extends IBaseUserEntity {

    Set<R> getRoles();
}
