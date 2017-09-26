package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.api.BaseReadOnlyApiImplHelper;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.api.IBaseEntityCrudApi;
import ir.amv.os.vaseline.basics.apis.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudApiImplHelper {

    private BaseCrudApiImplHelper() {
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> Id save(
            IBaseEntityCrudApi<E> api,
            IBaseCrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        api.preSave(entity);
        Id id = dao.save(entity);
        api.postSave(entity);
        return id;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<Id> saveBatch(
            IBaseEntityCrudApi<E> api,
            IBaseCrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        List<Id> result = new ArrayList<Id>();
        if (entities != null) {
            for (E entity : entities) {
                Id id = dao.save(entity);
                result.add(id);
            }
        }
        return result;
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void update(
            IBaseEntityCrudApi<E> api,
            IBaseCrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        api.preUpdate(entity);
        dao.update(entity);
        api.postUpdate(entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void updateBatch(
            IBaseEntityCrudApi<E> api,
            IBaseCrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                dao.update(entity);
            }
        }
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void delete(
            IBaseEntityCrudApi<E> api,
            IBaseCrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        api.preDelete(entity);
        dao.delete(entity);
        api.postDelete(entity);
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void deleteBatch(
            IBaseEntityCrudApi<E> api,
            IBaseCrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                delete(api, dao, entity);
            }
        }
    }

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void delete(
            IBaseEntityCrudApi<E> api,
            IBaseCrudDao<E, D, Id> dao,
            Id id)
            throws BaseVaselineServerException {
        E byId = BaseReadOnlyApiImplHelper.getById(api, dao, id);
        delete(api, dao, byId);
    }

}
