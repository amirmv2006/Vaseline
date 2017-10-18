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

    @Override
    default Id save(E entity) throws BaseVaselineServerException {
        preSave(entity);
        Id id = getWriteDaoFor(getCategoryForEntity(entity)).save(entity);
        postSave(entity);
        return id;
    }

    @Override
    default void update(E entity) throws BaseVaselineServerException {
        preUpdate(entity);
        getWriteDaoFor(getCategoryForEntity(entity)).update(entity);
        postUpdate(entity);
    }

    @Override
    default void delete(E entity) throws BaseVaselineServerException {
        preDelete(entity);
        getWriteDaoFor(getCategoryForEntity(entity)).delete(entity);
        postDelete(entity);
    }

    /**
     * @inheritDoc
     * @deprecated use {@link #getWriteDaoFor(Object)}
     */
    @Override
    default Dao getWriteDao() {
        throw new VaselineFeatureNotSupportedException();
    }

    Category getCategoryForEntity(final E entity) throws BaseVaselineServerException;
    Dao getWriteDaoFor(Category category) throws BaseVaselineServerException;
}
