package ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.business.apis.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.crud.IBaseCrudDao;

import java.io.Serializable;

public interface IBaseImplementedMultiDaoCrudApi<E extends IBaseEntity<Id>, Id extends Serializable, Category>
        extends IBaseImplementedMultiDaoReadOnlyApi<E, Id, Category>, IBaseImplementedCrudApi<E, Id> {

    @Override
    default Id save(E entity) throws BaseVaselineServerException {
        preSave(entity);
        Id id = getWriteDaoFor(entity).save(entity);
        postSave(entity);
        return id;
    }

    @Override
    default void update(E entity) throws BaseVaselineServerException {
        preUpdate(entity);
        getWriteDaoFor(entity).update(entity);
        postUpdate(entity);
    }

    @Override
    default void delete(E entity) throws BaseVaselineServerException {
        preDelete(entity);
        getWriteDaoFor(entity).delete(entity);
        postDelete(entity);
    }

    @Override
    default IBaseCrudDao<E, ?, Id> getWriteDao() {
        throw new VaselineFeatureNotSupportedException();
    }

    IBaseCrudDao<E, ?, Id> getWriteDaoFor(E entity) throws BaseVaselineServerException;
}
