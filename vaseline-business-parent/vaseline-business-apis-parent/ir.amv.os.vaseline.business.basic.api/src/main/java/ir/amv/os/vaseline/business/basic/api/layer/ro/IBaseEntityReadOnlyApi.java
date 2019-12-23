package ir.amv.os.vaseline.business.basic.api.layer.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.base.IBaseApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseEntityReadOnlyApi<E extends IBaseBusinessModel<?>> extends IBaseApi {

    void postGet(E entity) throws BaseBusinessException;

    Class<? extends E> getEntityClass();

    E newEntity() throws BaseBusinessException;
}
