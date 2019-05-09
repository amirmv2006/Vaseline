package ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base;

import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserEntity;

/**
 * @author Amir
 */
public interface IBaseUserDao<U extends IBaseUserEntity>
        extends IBaseReadOnlyDao<Long, U>{

    U getUserByUsername(String username);
}
