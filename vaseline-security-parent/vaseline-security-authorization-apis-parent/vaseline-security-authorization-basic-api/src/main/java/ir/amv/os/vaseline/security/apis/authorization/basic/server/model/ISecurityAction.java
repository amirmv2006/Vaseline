package ir.amv.os.vaseline.security.apis.authorization.basic.server.model;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;

/**
 * @author Amir
 */
public interface ISecurityAction<SA extends ISecurityAction>
        extends IBaseEntity<Long> {

    String getActionTreeName();
    SA getParent();
}

