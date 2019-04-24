package ir.amv.os.vaseline.security.authorization.business.def;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.business.api.IBaseNotSecuredCrudApi;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultNotSecuredCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseCrudDao<E, Id>>
        extends IDefaultCrudApi<E, Id, Dao>,
        IDefaultNotSecuredReadOnlyApi<E, Id, Dao>,
        IBaseNotSecuredCrudApi<E, Id> {

    @Override
    @Transactional
    default Id saveNotSecured(E entity) throws BaseVaselineServerException {
        return IDefaultCrudApi.super.save(entity);
    }
    @Override
    @Transactional
    default List<Id> saveBatchNotSecured(List<E> entities) throws  BaseVaselineServerException {
        return IDefaultCrudApi.super.saveBatch(entities);
    }

    @Override
    @Transactional
    default void updateNotSecured(E entity) throws BaseVaselineServerException {
        IDefaultCrudApi.super.update(entity);
    }
    @Override
    @Transactional
    default void updateBatchNotSecured(List<E> entities) throws  BaseVaselineServerException {
        IDefaultCrudApi.super.updateBatch(entities);
    }

    @Override
    @Transactional
    default void deleteNotSecured(E entity) throws BaseVaselineServerException {
        IDefaultCrudApi.super.delete(entity);
    }
    @Override
    @Transactional
    default void deleteBatchNotSecured(List<E> entities) throws  BaseVaselineServerException {
        IDefaultCrudApi.super.deleteBatch(entities);
    }
    @Override
    @Transactional
    default void deleteNotSecured(Id id) throws BaseVaselineServerException {
        IDefaultCrudApi.super.delete(id);
    }
}
