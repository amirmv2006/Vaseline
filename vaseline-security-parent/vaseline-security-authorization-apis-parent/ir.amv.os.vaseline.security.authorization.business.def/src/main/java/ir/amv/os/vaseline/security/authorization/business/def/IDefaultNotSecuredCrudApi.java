package ir.amv.os.vaseline.security.authorization.business.def;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.business.api.IBaseNotSecuredCrudApi;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultNotSecuredCrudApi<E extends IBaseBusinessModel<Id>, Id extends Serializable, Dao extends
        IBaseCrudDao<Id, E>>
        extends IDefaultCrudApi<Id, E, Dao>,
        IDefaultNotSecuredReadOnlyApi<E, Id, Dao>,
        IBaseNotSecuredCrudApi<E, Id> {

    @Override
    @Transactional
    default Id saveNotSecured(E entity) throws BaseBusinessException {
        return IDefaultCrudApi.super.save(entity);
    }
    @Override
    @Transactional
    default List<Id> saveBatchNotSecured(List<E> entities) throws BaseBusinessException {
        return IDefaultCrudApi.super.saveBatch(entities);
    }

    @Override
    @Transactional
    default void updateNotSecured(E entity) throws BaseBusinessException {
        IDefaultCrudApi.super.update(entity);
    }
    @Override
    @Transactional
    default void updateBatchNotSecured(List<E> entities) throws BaseBusinessException {
        IDefaultCrudApi.super.updateBatch(entities);
    }

    @Override
    @Transactional
    default void deleteNotSecured(E entity) throws BaseBusinessException {
        IDefaultCrudApi.super.delete(entity);
    }
    @Override
    @Transactional
    default void deleteBatchNotSecured(List<E> entities) throws BaseBusinessException {
        IDefaultCrudApi.super.deleteBatch(entities);
    }
    @Override
    @Transactional
    default void deleteNotSecured(Id id) throws BaseBusinessException {
        IDefaultCrudApi.super.delete(id);
    }
}
