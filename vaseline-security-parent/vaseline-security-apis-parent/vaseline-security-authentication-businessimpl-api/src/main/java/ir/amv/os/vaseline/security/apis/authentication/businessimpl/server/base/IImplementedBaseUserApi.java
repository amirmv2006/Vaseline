package ir.amv.os.vaseline.security.apis.authentication.businessimpl.server.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authentication.business.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

/**
 * @author Amir
 */
public interface IImplementedBaseUserApi<U extends IBaseUserEntity, Dao extends IBaseUserDao<U>>
        extends IBaseImplementedReadOnlyApi<U, Long, Dao>, IBaseUserApi<U> {

    @Override
    default U getUserByUsername(String username) throws BaseVaselineServerException {
        U findById = getDao().getUserByUsername(username);
        postGet(findById);
        return findById;
    }
}
