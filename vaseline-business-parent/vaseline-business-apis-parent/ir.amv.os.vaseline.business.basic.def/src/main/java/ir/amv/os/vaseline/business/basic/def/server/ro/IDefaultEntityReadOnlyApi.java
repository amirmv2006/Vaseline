package ir.amv.os.vaseline.business.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.business.basic.def.server.base.IDefaultApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IDefaultEntityReadOnlyApi<E extends IBaseBusinessModel<?>>
        extends IBaseEntityReadOnlyApi<E>, IDefaultApi {

    @Override
    default void postGet(E entity) throws BaseBusinessException {

    }
}
