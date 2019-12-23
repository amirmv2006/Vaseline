package ir.amv.os.vaseline.business.multidao.def.server.crud;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.business.multidao.def.server.ro.IDefaultMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface IDefaultMultiDaoCrudApi<Category, Id extends Serializable, E extends IBaseBusinessModel<Id>,
        Dao extends IBaseCrudDao<Id, E>>
        extends IDefaultMultiDaoReadOnlyApi<Category, Id, E, Dao>, IDefaultCrudApi<Id, E, Dao> {
    Category getCategoryForEntity(final E entity) throws BaseBusinessException;

    @Override
    @Transactional
    default Id save(E entity) throws BaseBusinessException {
        preSave(entity);
        Id id = getDaoFor(getCategoryForEntity(entity)).save(entity);
        postSave(entity);
        return id;
    }

    @Override
    @Transactional
    default void update(E entity) throws BaseBusinessException {
        preUpdate(entity);
        getDaoFor(getCategoryForEntity(entity)).update(entity);
        postUpdate(entity);
    }

    @Override
    @Transactional
    default void delete(E entity) throws BaseBusinessException {
        preDelete(entity);
        getDaoFor(getCategoryForEntity(entity)).delete(entity);
        postDelete(entity);
    }

}
