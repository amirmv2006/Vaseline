package ir.amv.os.vaseline.business.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.business.basic.def.server.base.IDefaultApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IDefaultEntityReadOnlyApi<E extends IBaseEntity<?>>
        extends IBaseEntityReadOnlyApi<E>, IDefaultApi {

    @Override
    default void postGet(E entity) throws BaseVaselineServerException {

    }
}
