package ir.amv.os.vaseline.security.authorization.rbac.model.api.user;

import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserBusinessModel;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;

import java.util.Set;

/**
 * @author Amir
 */
public interface ISecurityUserWithRoleBusinessModel<R extends ISecurityRole>
        extends IBaseUserBusinessModel {

    Set<R> getRoles();
}
