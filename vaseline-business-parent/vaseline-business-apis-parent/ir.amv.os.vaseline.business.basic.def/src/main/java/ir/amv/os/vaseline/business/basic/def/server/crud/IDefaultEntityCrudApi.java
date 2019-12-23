package ir.amv.os.vaseline.business.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.model.hascreatedate.IBaseHasCreateDateBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.model.hasmodifydate.IBaseHasModifyDateBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.shared.util.date.DateUtil;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseEntityCrudApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IDefaultEntityCrudApi<E extends IBaseBusinessModel<?>>
        extends IBaseEntityCrudApi<E> {

    @Override
    default void preSave(E entity) throws BaseBusinessException {
        if (entity instanceof IBaseHasCreateDateBusinessModel<?>) {
            IBaseHasCreateDateBusinessModel<?> hasCreateDateEntity = (IBaseHasCreateDateBusinessModel<?>) entity;
            hasCreateDateEntity.setCreateDate(DateUtil.newDate());
        }
    }

    @Override
    default void postSave(E entity) throws BaseBusinessException {
    }

    @Override
    default void preUpdate(E entity) throws BaseBusinessException {
        if (entity instanceof IBaseHasModifyDateBusinessModel<?>) {
            IBaseHasModifyDateBusinessModel<?> hasModifyDateEntity = (IBaseHasModifyDateBusinessModel<?>) entity;
            hasModifyDateEntity.setModifyDate(DateUtil.newDate());
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
