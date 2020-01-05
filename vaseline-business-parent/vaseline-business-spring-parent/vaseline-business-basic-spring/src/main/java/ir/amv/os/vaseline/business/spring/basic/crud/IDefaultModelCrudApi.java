package ir.amv.os.vaseline.business.spring.basic.crud;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.hascreatedate.IBaseHasCreateDateBusinessEntity;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.hasmodifydate.IBaseHasModifyDateBusinessEntity;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseModelWriteApi;

import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IDefaultModelCrudApi<E extends IBaseBusinessEntity<?, E>>
        extends IBaseModelWriteApi<E> {

    @Override
    default void preSave(E entity) throws BaseBusinessException {
        if (entity instanceof IBaseHasCreateDateBusinessEntity<?, ?>) {
            IBaseHasCreateDateBusinessEntity<?, ?> hasCreateDateEntity =
                    (IBaseHasCreateDateBusinessEntity<?, ?>) entity;
            hasCreateDateEntity.setCreateDate(new Date());
        }
    }

    @Override
    default void postSave(E entity) throws BaseBusinessException {
    }

    @Override
    default void preUpdate(E entity) throws BaseBusinessException {
        if (entity instanceof IBaseHasModifyDateBusinessEntity<?, ?>) {
            IBaseHasModifyDateBusinessEntity<?, ?> hasModifyDateEntity =
                    (IBaseHasModifyDateBusinessEntity<?, ?>) entity;
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
