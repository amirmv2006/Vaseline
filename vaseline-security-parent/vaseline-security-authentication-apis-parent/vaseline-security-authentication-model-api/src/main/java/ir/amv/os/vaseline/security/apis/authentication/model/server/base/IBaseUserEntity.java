package ir.amv.os.vaseline.security.apis.authentication.model.server.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;

/**
 * @author Amir
 */
public interface IBaseUserEntity
        extends IBaseEntity<Long> {
    String getUsername();
}
