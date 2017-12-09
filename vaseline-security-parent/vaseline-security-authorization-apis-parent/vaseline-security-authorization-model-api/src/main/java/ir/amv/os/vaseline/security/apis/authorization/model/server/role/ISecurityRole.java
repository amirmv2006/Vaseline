package ir.amv.os.vaseline.security.apis.authorization.model.server.role;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;

/**
 * @author Amir
 */
public interface ISecurityRole
        extends IBaseEntity<Long> {

    String getRoleName();

}
