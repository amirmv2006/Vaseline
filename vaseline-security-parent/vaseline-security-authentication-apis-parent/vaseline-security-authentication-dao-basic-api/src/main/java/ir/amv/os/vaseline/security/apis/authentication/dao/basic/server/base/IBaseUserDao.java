package ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base;

import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

/**
 * @author Amir
 */
public interface IBaseUserDao<U extends IBaseUserEntity>
        extends IBaseReadOnlyDao<U, Long>{

    U getUserByUsername(String username);
}
