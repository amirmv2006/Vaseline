package ir.amv.os.vaseline.business.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.entity.hascreatedate.IBaseHasCreateDateEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.entity.hasmodifydate.IBaseHasModifyDateEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.util.date.DateUtil;
import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseEntityCrudApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseImplementedEntityCrudApi<E extends IBaseEntity<?>>
        extends IBaseEntityCrudApi<E> {

    @Override
    default void preSave(E entity) throws BaseVaselineServerException {
        if (entity instanceof IBaseHasCreateDateEntity<?>) {
            IBaseHasCreateDateEntity<?> hasCreateDateEntity = (IBaseHasCreateDateEntity<?>) entity;
            hasCreateDateEntity.setCreateDate(DateUtil.newDate());
        }
    }

    @Override
    default void postSave(E entity) throws BaseVaselineServerException {
    }

    @Override
    default void preUpdate(E entity) throws BaseVaselineServerException {
        if (entity instanceof IBaseHasModifyDateEntity<?>) {
            IBaseHasModifyDateEntity<?> hasModifyDateEntity = (IBaseHasModifyDateEntity<?>) entity;
            hasModifyDateEntity.setModifyDate(DateUtil.newDate());
        }
    }

    @Override
    default void postUpdate(E entity) throws BaseVaselineServerException {
    }

    @Override
    default void preDelete(E entity) throws BaseVaselineServerException {
    }

    @Override
    default void postDelete(E entity) throws BaseVaselineServerException {
    }
}
