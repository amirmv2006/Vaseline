package ir.amv.os.vaseline.business.multidao.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.def.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.basic.def.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.business.multidao.def.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;

import java.io.Serializable;

public interface IBaseImplementedMultiDaoCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Category,
        Dao extends IBaseCrudDao<E, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedCrudApi<E, Id, Dao> {
    Category getCategoryForEntity(final E entity) throws BaseVaselineServerException;

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default Id save(E entity) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Id>) () -> {
            preSave(entity);
            Id id = getDaoFor(getCategoryForEntity(entity)).save(entity);
            postSave(entity);
            return id;
        });
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void update(E entity) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>) () -> {
            preUpdate(entity);
            getDaoFor(getCategoryForEntity(entity)).update(entity);
            postUpdate(entity);
            return null;
        });
    }

    @Override
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE
    })
    default void delete(E entity) throws BaseVaselineServerException {
        doBusinessAction((IBusinessFunctionZero<Void>) () -> {
            preDelete(entity);
            getDaoFor(getCategoryForEntity(entity)).delete(entity);
            postDelete(entity);
            return null;
        });
    }

}
