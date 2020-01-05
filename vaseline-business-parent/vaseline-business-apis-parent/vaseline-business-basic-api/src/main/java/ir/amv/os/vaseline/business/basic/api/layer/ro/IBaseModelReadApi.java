package ir.amv.os.vaseline.business.basic.api.layer.ro;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.base.IBaseApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseModelReadApi<E extends IBaseBusinessEntity<?, E>> extends IBaseApi {

    void postGet(E entity) throws BaseBusinessException;

    Class<E> getModelClass();

}
