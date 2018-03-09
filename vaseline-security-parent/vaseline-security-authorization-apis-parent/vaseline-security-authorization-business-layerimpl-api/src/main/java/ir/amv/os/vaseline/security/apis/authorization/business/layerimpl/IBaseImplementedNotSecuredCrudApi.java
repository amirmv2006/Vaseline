package ir.amv.os.vaseline.security.apis.authorization.business.layerimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.annot.NoAuthorization;
import ir.amv.os.vaseline.security.apis.authorization.business.layer.IBaseNotSecuredCrudApi;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedNotSecuredCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseCrudDao<E, Id>>
        extends IBaseImplementedCrudApi<E, Id, Dao>,
        IBaseImplementedNotSecuredReadOnlyApi<E, Id, Dao>,
        IBaseNotSecuredCrudApi<E, Id> {

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default Id saveNotSecured(E entity) throws BaseVaselineServerException {
        return IBaseImplementedCrudApi.super.save(entity);
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default List<Id> saveBatchNotSecured(List<E> entities) throws  BaseVaselineServerException {
        return IBaseImplementedCrudApi.super.saveBatch(entities);
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void updateNotSecured(E entity) throws BaseVaselineServerException {
        IBaseImplementedCrudApi.super.update(entity);
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void updateBatchNotSecured(List<E> entities) throws  BaseVaselineServerException {
        IBaseImplementedCrudApi.super.updateBatch(entities);
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void deleteNotSecured(E entity) throws BaseVaselineServerException {
        IBaseImplementedCrudApi.super.delete(entity);
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void deleteBatchNotSecured(List<E> entities) throws  BaseVaselineServerException {
        IBaseImplementedCrudApi.super.deleteBatch(entities);
    }
    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void deleteNotSecured(Id id) throws BaseVaselineServerException {
        IBaseImplementedCrudApi.super.delete(id);
    }
}
