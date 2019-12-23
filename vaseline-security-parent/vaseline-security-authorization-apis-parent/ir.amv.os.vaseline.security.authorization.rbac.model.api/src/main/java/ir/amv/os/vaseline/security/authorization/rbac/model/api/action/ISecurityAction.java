package ir.amv.os.vaseline.security.authorization.rbac.model.api.action;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;

/**
 * @author Amir
 */
public interface ISecurityAction<A extends ISecurityAction>
        extends IBaseBusinessModel<Long> {

    String getActionTreeName();
    A getParent();
}

