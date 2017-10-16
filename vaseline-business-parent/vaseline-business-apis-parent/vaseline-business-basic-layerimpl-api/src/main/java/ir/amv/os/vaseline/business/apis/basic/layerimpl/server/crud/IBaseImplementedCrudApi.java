package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.crud.IBaseCrudDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseImplementedCrudApi<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseCrudApi<E, Id>, IBaseImplementedEntityCrudApi<E>, IBaseImplementedReadOnlyApi<E, Id> {

    IBaseCrudDao<E, ?, Id> getWriteDao();

    default Id save(E entity) throws BaseVaselineServerException {
        preSave(entity);
        Id id = getWriteDao().save(entity);
        postSave(entity);
        return id;
    }

    default List<Id> saveBatch(List<E> entities) throws BaseVaselineServerException {
        List<Id> result = new ArrayList<>();
        if (entities != null) {
            for (E entity : entities) {
                Id id = save(entity);
                result.add(id);
            }
        }
        return result;
    }

    default void update(E entity) throws BaseVaselineServerException {
        preUpdate(entity);
        getWriteDao().update(entity);
        postUpdate(entity);
    }

    default void updateBatch(List<E> entities) throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                update(entity);
            }
        }
    }

    default void delete(E entity) throws BaseVaselineServerException {
        preDelete(entity);
        getWriteDao().delete(entity);
        postDelete(entity);
    }

    default void deleteBatch(List<E> entities) throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                delete(entity);
            }
        }
    }

    default void delete(Id id) throws BaseVaselineServerException {
        E byId = getById(id);
        delete(byId);
    }

}
