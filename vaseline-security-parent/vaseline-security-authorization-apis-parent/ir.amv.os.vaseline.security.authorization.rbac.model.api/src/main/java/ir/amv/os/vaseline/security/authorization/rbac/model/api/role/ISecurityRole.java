package ir.amv.os.vaseline.security.authorization.rbac.model.api.role;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.action.ISecurityAction;

import java.util.Set;

/**
 * @author Amir
 */
public interface ISecurityRole<A extends ISecurityAction<A>>
        extends IBaseBusinessModel<Long> {

    String getRoleName();
    Set<A> getActions();

}
