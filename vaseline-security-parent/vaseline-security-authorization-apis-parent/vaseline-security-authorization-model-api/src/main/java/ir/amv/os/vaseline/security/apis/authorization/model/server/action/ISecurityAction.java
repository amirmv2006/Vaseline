package ir.amv.os.vaseline.security.apis.authorization.model.server.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;

/**
 * @author Amir
 */
public interface ISecurityAction
        extends IBaseEntity<Long> {

    String getActionTreeName();
    ISecurityAction getParent();
}

