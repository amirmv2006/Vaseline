package ir.amv.os.vaseline.security.authentication.model.api.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;

/**
 * @author Amir
 */
public interface IBaseUserEntity
        extends IBaseEntity<Long> {
    String getUsername();
}
