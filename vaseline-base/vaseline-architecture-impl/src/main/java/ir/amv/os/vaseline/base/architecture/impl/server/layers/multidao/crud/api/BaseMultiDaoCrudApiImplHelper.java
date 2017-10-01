package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.crud.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.api.BaseCrudApiImplHelper;
import ir.amv.os.vaseline.data.apis.dao.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.crud.IBaseMultiDaoCrudApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public abstract class BaseMultiDaoCrudApiImplHelper {

    private BaseMultiDaoCrudApiImplHelper() {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> Id save(
            IBaseMultiDaoCrudApi<E, D, Id> api,
            String coreId,
            E entity)
            throws BaseVaselineServerException {
        IBaseCrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseCrudApiImplHelper.save(api, dao, entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<Id> saveBatch(
            IBaseMultiDaoCrudApi<E, D, Id> api,
            String coreId,
            List<E> entities)
            throws BaseVaselineServerException {
        IBaseCrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseCrudApiImplHelper.saveBatch(api, dao, entities);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void update(
            IBaseMultiDaoCrudApi<E, D, Id> api,
            String coreId,
            E entity)
            throws BaseVaselineServerException {
        IBaseCrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudApiImplHelper.update(api, dao, entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void updateBatch(
            IBaseMultiDaoCrudApi<E, D, Id> api,
            String coreId,
            List<E> entities)
            throws BaseVaselineServerException {
        IBaseCrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudApiImplHelper.updateBatch(api, dao, entities);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void delete(
            IBaseMultiDaoCrudApi<E, D, Id> api,
            String coreId,
            E entity)
            throws BaseVaselineServerException {
        IBaseCrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudApiImplHelper.delete(api, dao, entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void deleteBatch(
            IBaseMultiDaoCrudApi<E, D, Id> api,
            String coreId,
            List<E> entities)
            throws BaseVaselineServerException {
        IBaseCrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudApiImplHelper.deleteBatch(api, dao, entities);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void delete(
            IBaseMultiDaoCrudApi<E, D, Id> api,
            String coreId,
            Id id)
            throws BaseVaselineServerException {
        IBaseCrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudApiImplHelper.delete(api, dao, id);
    }

}
