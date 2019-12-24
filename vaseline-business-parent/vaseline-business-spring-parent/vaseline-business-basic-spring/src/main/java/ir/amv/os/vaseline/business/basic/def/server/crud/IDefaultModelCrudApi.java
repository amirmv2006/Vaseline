package ir.amv.os.vaseline.business.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.hascreatedate.IBaseHasCreateDateBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.hasmodifydate.IBaseHasModifyDateBusinessModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseModelCrudApi;

import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IDefaultModelCrudApi<E extends IBaseBusinessModel<?>>
        extends IBaseModelCrudApi<E> {

    @Override
    default void preSave(E entity) throws BaseBusinessException {
        if (entity instanceof IBaseHasCreateDateBusinessModel<?>) {
            IBaseHasCreateDateBusinessModel<?> hasCreateDateEntity = (IBaseHasCreateDateBusinessModel<?>) entity;
            hasCreateDateEntity.setCreateDate(new Date());
        }
    }

    @Override
    default void postSave(E entity) throws BaseBusinessException {
    }

    @Override
    default void preUpdate(E entity) throws BaseBusinessException {
        if (entity instanceof IBaseHasModifyDateBusinessModel<?>) {
            IBaseHasModifyDateBusinessModel<?> hasModifyDateEntity = (IBaseHasModifyDateBusinessModel<?>) entity;
            hasModifyDateEntity.setModifyDate(new Date());
        }
    }

    @Override
    default void postUpdate(E entity) throws BaseBusinessException {
    }

    @Override
    default void preDelete(E entity) throws BaseBusinessException {
    }

    @Override
    default void postDelete(E entity) throws BaseBusinessException {
    }
}
