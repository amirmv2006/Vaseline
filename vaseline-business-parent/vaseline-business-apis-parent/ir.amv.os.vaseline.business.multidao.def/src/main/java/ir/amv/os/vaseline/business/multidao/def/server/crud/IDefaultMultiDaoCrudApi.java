package ir.amv.os.vaseline.business.multidao.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.business.multidao.def.server.ro.IDefaultMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface IDefaultMultiDaoCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Category,
        Dao extends IBaseCrudDao<Id, E>>
        extends IDefaultMultiDaoReadOnlyApi<E, Id, Category, Dao>, IDefaultCrudApi<E, Id, Dao> {
    Category getCategoryForEntity(final E entity) throws BaseVaselineServerException;

    @Override
    @Transactional
    default Id save(E entity) throws BaseVaselineServerException {
        preSave(entity);
        Id id = getDaoFor(getCategoryForEntity(entity)).save(entity);
        postSave(entity);
        return id;
    }

    @Override
    @Transactional
    default void update(E entity) throws BaseVaselineServerException {
        preUpdate(entity);
        getDaoFor(getCategoryForEntity(entity)).update(entity);
        postUpdate(entity);
    }

    @Override
    @Transactional
    default void delete(E entity) throws BaseVaselineServerException {
        preDelete(entity);
        getDaoFor(getCategoryForEntity(entity)).delete(entity);
        postDelete(entity);
    }

}
