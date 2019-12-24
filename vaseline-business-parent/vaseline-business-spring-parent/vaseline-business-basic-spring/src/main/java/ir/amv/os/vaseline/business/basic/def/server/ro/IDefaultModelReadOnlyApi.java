package ir.amv.os.vaseline.business.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseModelReadOnlyApi;
import ir.amv.os.vaseline.business.basic.def.server.base.IDefaultApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IDefaultModelReadOnlyApi<E extends IBaseBusinessModel<?>>
        extends IBaseModelReadOnlyApi<E>, IDefaultApi {

    @Override
    default void postGet(E entity) throws BaseBusinessException {
    }
}
