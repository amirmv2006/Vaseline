package ir.amv.os.vaseline.security.authorization.rbac.model.api.user;

import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserEntity;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

import java.util.Set;

/**
 * @author Amir
 */
public interface ISecurityUserWithRoleEntity<R extends ISecurityRole>
        extends IBaseUserEntity {

    Set<R> getRoles();
}
