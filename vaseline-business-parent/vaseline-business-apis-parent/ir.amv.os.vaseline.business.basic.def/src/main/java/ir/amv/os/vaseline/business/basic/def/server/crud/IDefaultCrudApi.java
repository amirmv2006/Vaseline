package ir.amv.os.vaseline.business.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IDefaultCrudApi<Id extends Serializable, E extends IBaseBusinessModel<Id>, Dao extends
        IBaseCrudDao<Id, E>>
        extends IBaseCrudApi<Id, E>, IDefaultEntityCrudApi<E>, IDefaultReadOnlyApi<Id, E, Dao> {

    @Override
    @Transactional
    default Id save(E entity) throws BaseBusinessException {
        preSave(entity);
        Id id = getDao().save(entity);
        postSave(entity);
        return id;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default List<Id> saveBatch(List<E> entities) throws BaseBusinessException {
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
    default void update(E entity) throws BaseBusinessException {
        preUpdate(entity);
        getDao().update(entity);
        postUpdate(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void updateBatch(List<E> entities) throws BaseBusinessException {
        if (entities != null) {
            for (E entity : entities) {
                update(entity);
            }
        }
    }

    @Override
    @Transactional
    default void delete(E entity) throws BaseBusinessException {
        preDelete(entity);
        getDao().delete(entity);
        postDelete(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void deleteBatch(List<E> entities) throws BaseBusinessException {
        if (entities != null) {
            for (E entity : entities) {
                delete(entity);
            }
        }
    }

    @Override
    @Transactional
    default void delete(Id id) throws BaseBusinessException {
        E byId = getById(id);
        delete(byId);
    }

}
