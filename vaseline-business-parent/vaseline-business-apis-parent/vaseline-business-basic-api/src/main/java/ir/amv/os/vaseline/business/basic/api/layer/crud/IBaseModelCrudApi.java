package ir.amv.os.vaseline.business.basic.api.layer.crud;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseModelReadOnlyApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseModelCrudApi<E extends IBaseBusinessModel<?>>
        extends IBaseModelReadOnlyApi<E> {

    void preSave(E entity) throws BaseBusinessException;
    void postSave(E entity) throws BaseBusinessException;

    void preUpdate(E entity) throws BaseBusinessException;
    void postUpdate(E entity) throws BaseBusinessException;

    void preDelete(E entity) throws BaseBusinessException;
    void postDelete(E entity) throws BaseBusinessException;
}
