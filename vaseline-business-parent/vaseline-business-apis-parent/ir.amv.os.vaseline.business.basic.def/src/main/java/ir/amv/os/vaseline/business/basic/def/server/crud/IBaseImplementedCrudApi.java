package ir.amv.os.vaseline.business.basic.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.basic.def.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.basic.def.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseImplementedCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseCrudDao<E, Id>>
        extends IBaseCrudApi<E, Id>, IBaseImplementedEntityCrudApi<E>, IBaseImplementedReadOnlyApi<E, Id, Dao> {

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default Id save(E entity) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Id>) () -> {
            preSave(entity);
            Id id = getDao().save(entity);
            postSave(entity);
            return id;
        });
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default List<Id> saveBatch(List<E> entities) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<Id>>) () -> {
            List<Id> result = new ArrayList<>();
            if (entities != null) {
                for (E entity : entities) {
                    Id id = save(entity);
                    result.add(id);
                }
            }
            return result;
        });
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void update(E entity) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>) () -> {
            preUpdate(entity);
            getDao().update(entity);
            postUpdate(entity);
            return null;
        });
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void updateBatch(List<E> entities) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>) () -> {
            if (entities != null) {
                for (E entity : entities) {
                    update(entity);
                }
            }
            return null;
        });
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void delete(E entity) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>) () -> {
            preDelete(entity);
            getDao().delete(entity);
            postDelete(entity);
            return null;
        });
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void deleteBatch(List<E> entities) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>) () -> {
            if (entities != null) {
                for (E entity : entities) {
                    delete(entity);
                }
            }
            return null;
        });
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void delete(Id id) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>) () -> {
            E byId = getById(id);
            delete(byId);
            return null;
        });
    }

}
