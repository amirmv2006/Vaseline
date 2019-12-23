package ir.amv.os.vaseline.business.basic.api.layer.crud;

import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseEntityCrudApi<E extends IBaseBusinessModel<?>>
        extends IBaseEntityReadOnlyApi<E> {

    void preSave(E entity) throws BaseBusinessException;
    void postSave(E entity) throws BaseBusinessException;

    void preUpdate(E entity) throws BaseBusinessException;
    void postUpdate(E entity) throws BaseBusinessException;

    void preDelete(E entity) throws BaseBusinessException;
    void postDelete(E entity) throws BaseBusinessException;
}
