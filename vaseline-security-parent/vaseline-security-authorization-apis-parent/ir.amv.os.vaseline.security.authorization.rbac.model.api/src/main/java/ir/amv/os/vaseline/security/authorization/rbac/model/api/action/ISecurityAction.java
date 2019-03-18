package ir.amv.os.vaseline.security.authorization.rbac.model.api.action;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;

/**
 * @author Amir
 */
public interface ISecurityAction<A extends ISecurityAction>
        extends IBaseEntity<Long> {

    String getActionTreeName();
    A getParent();
}

