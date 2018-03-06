package ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.action.ISecurityAction;

import java.util.Set;

/**
 * @author Amir
 */
public interface ISecurityRole<A extends ISecurityAction<A>>
        extends IBaseEntity<Long> {

    String getRoleName();
    Set<A> getActions();

}
