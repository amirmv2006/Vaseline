package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;

import java.io.Serializable;

public interface IBaseImplementedMultiDaoCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Category,
        Dao extends IBaseCrudDao<E, Id>>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category, Dao>, IBaseImplementedCrudApi<E, Id, Dao> {
    Category getCategoryForEntity(final E entity) throws BaseVaselineServerException;

    @Override
    default Id save(E entity) throws BaseVaselineServerException {
        preSave(entity);
        Id id = getDaoFor(getCategoryForEntity(entity)).save(entity);
        postSave(entity);
        return id;
    }

    @Override
    default void update(E entity) throws BaseVaselineServerException {
        preUpdate(entity);
        getDaoFor(getCategoryForEntity(entity)).update(entity);
        postUpdate(entity);
    }

    @Override
    default void delete(E entity) throws BaseVaselineServerException {
        preDelete(entity);
        getDaoFor(getCategoryForEntity(entity)).delete(entity);
        postDelete(entity);
    }

}
