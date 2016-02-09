package ir.amv.os.vaseline.base.architecture.impl.server.layers.crud.api;

import ir.amv.os.vaseline.base.architecture.server.layers.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.architecture.server.layers.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudApiImplHelper {

    private BaseCrudApiImplHelper() {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            Id save(
            IBaseCrudApi<E, D, Id> api,
            IBaseCrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        preSave(api, entity);
        Id id = dao.save(entity);
        postSave(api, entity);
        return id;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<Id> saveBatch(
            IBaseCrudApi<E, D, Id> api,
            IBaseCrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        List<Id> result = new ArrayList<Id>();
        if (entities != null) {
            for (E entity : entities) {
                Id id = api.save(entity);
                result.add(id);
            }
        }
        return result;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void preSave(
            IBaseCrudApi<E, D, Id> api,
            E entity)
            throws BaseVaselineServerException {
        api.preSave(entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void postSave(
            IBaseCrudApi<E, D, Id> api,
            E entity)
            throws BaseVaselineServerException {
        api.postSave(entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void update(
            IBaseCrudApi<E, D, Id> api,
            IBaseCrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        preUpdate(api, entity);
        dao.update(entity);
        postUpdate(api, entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void updateBatch(
            IBaseCrudApi<E, D, Id> api,
            IBaseCrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                api.update(entity);
            }
        }
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void preUpdate(
            IBaseCrudApi<E, D, Id> api,
            E entity)
            throws BaseVaselineServerException {
        api.preUpdate(entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void postUpdate(
            IBaseCrudApi<E, D, Id> api,
            E entity)
            throws BaseVaselineServerException {
        api.postUpdate(entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void delete(
            IBaseCrudApi<E, D, Id> api,
            IBaseCrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        preDelete(api, entity);
        dao.delete(entity);
        postDelete(api, entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void deleteBatch(
            IBaseCrudApi<E, D, Id> api,
            IBaseCrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                api.delete(entity);
            }
        }
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void delete(
            IBaseCrudApi<E, D, Id> api,
            IBaseCrudDao<E, D, Id> dao,
            Id id)
            throws BaseVaselineServerException {
        E byId = api.getById(id);
        delete(api, dao, byId);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void preDelete(
            IBaseCrudApi<E, D, Id> api,
            E entity)
            throws BaseVaselineServerException {
        api.preDelete(entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void postDelete(
            IBaseCrudApi<E, D, Id> api,
            E entity)
            throws BaseVaselineServerException {
        api.postDelete(entity);
    }
}
