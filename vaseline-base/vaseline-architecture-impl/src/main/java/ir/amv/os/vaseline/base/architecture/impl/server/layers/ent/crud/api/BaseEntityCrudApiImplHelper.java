package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.crud.api;

import ir.amv.os.vaseline.basics.apis.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.hascreatedate.IBaseHasCreateDateEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.hasmodifydate.IBaseHasModifyDateEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityCrudApiImplHelper {

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void preSave(
            E entity)
            throws BaseVaselineServerException {
        if (entity instanceof IBaseHasCreateDateEntity<?>) {
            IBaseHasCreateDateEntity<?> hasCreateDateEntity = (IBaseHasCreateDateEntity<?>) entity;
            hasCreateDateEntity.setCreateDate(DateUtil.newDate());
        }
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postSave(
            E entity)
            throws BaseVaselineServerException {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void preUpdate(
            E entity)
            throws BaseVaselineServerException {
        if (entity instanceof IBaseHasModifyDateEntity<?>) {
            IBaseHasModifyDateEntity<?> hasModifyDateEntity = (IBaseHasModifyDateEntity<?>) entity;
            hasModifyDateEntity.setModifyDate(DateUtil.newDate());
        }
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postUpdate(
            E entity)
            throws BaseVaselineServerException {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void preDelete(
            E entity)
            throws BaseVaselineServerException {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postDelete(
            E entity)
            throws BaseVaselineServerException {
    }

}
