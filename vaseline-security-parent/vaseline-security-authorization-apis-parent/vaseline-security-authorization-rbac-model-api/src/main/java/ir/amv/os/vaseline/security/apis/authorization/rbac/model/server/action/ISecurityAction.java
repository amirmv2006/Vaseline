package ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;

/**
 * @author Amir
 */
public interface ISecurityAction<A extends ISecurityAction>
        extends IBaseEntity<Long> {

    String getActionTreeName();
    A getParent();
}

