package ir.amv.os.vaseline.business.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IDefaultCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseCrudDao<Id, E>>
        extends IBaseCrudApi<E, Id>, IDefaultEntityCrudApi<E>, IDefaultReadOnlyApi<E, Id, Dao> {

    @Override
    @Transactional
    default Id save(E entity) throws BaseVaselineServerException {
        preSave(entity);
        Id id = getDao().save(entity);
        postSave(entity);
        return id;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
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

    @Override
    @Transactional
    default void update(E entity) throws BaseVaselineServerException {
        preUpdate(entity);
        getDao().update(entity);
        postUpdate(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void updateBatch(List<E> entities) throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                update(entity);
            }
        }
    }

    @Override
    @Transactional
    default void delete(E entity) throws BaseVaselineServerException {
        preDelete(entity);
        getDao().delete(entity);
        postDelete(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void deleteBatch(List<E> entities) throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                delete(entity);
            }
        }
    }

    @Override
    @Transactional
    default void delete(Id id) throws BaseVaselineServerException {
        E byId = getById(id);
        delete(byId);
    }

}
