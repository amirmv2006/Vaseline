package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionOneImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface IBaseImplementedMultiDaoCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Category,
        Dao extends IBaseCrudDao<E, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedCrudApi<E, Id, Dao> {
    Category getCategoryForEntity(final E entity) throws BaseVaselineServerException;

    @Override
    default Id save(E entity) throws BaseVaselineServerException {
        Method saveMethod = getDeclaredMethod(IBaseImplementedMultiDaoCrudApi.class, "save", IBaseEntity.class);
        return doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), saveMethod, entity, e -> {
            preSave(e);
            Id id = getDaoFor(getCategoryForEntity(e)).save(e);
            postSave(e);
            return id;
        }, VaselineDbOpMetadata.WRITE));
    }

    @Override
    default void update(E entity) throws BaseVaselineServerException {
        Method updateMethod = getDeclaredMethod(IBaseImplementedMultiDaoCrudApi.class, "update", IBaseEntity.class);
        doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), updateMethod, entity, e -> {
            preUpdate(e);
            getDaoFor(getCategoryForEntity(e)).update(e);
            postUpdate(e);
            return null;
        }, VaselineDbOpMetadata.WRITE));
    }

    @Override
    default void delete(E entity) throws BaseVaselineServerException {
        Method deleteMethod = getDeclaredMethod(IBaseImplementedMultiDaoCrudApi.class, "delete", IBaseEntity.class);
        doBusinessAction(new BusinessFunctionOneImpl<>(
                getClass(), deleteMethod, entity, e -> {
            preDelete(e);
            getDaoFor(getCategoryForEntity(e)).delete(e);
            postDelete(e);
            return null;
        }, VaselineDbOpMetadata.WRITE));
    }

}
