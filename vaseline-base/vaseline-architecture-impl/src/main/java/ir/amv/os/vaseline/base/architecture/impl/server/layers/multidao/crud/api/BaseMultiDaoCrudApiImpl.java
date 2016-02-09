package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.crud.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.crud.api.BaseEntityCrudApiImplHelper;
import ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.ro.api.BaseMultiDaoReadOnlyApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.crud.IBaseMultiDaoCrudApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public abstract class BaseMultiDaoCrudApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseCrudDao<E, D, Id>>
        extends BaseMultiDaoReadOnlyApiImpl<E, D, Id, DAO>
        implements IBaseMultiDaoCrudApi<E, D, Id> {

    @Override
    @Transactional
    public Id save(String coreId, E entity) throws BaseVaselineServerException {
        return BaseMultiDaoCrudApiImplHelper.save(this, coreId, entity);
    }

    @Override
    @Transactional
    public List<Id> saveBatch(String coreId, List<E> entities) throws BaseVaselineServerException {
        return BaseMultiDaoCrudApiImplHelper.saveBatch(this, coreId, entities);
    }

    @Override
    @Transactional
    public void update(String coreId, E entity) throws BaseVaselineServerException {
        BaseMultiDaoCrudApiImplHelper.update(this, coreId, entity);
    }

    @Override
    @Transactional
    public void updateBatch(String coreId, List<E> entities) throws BaseVaselineServerException {
        BaseMultiDaoCrudApiImplHelper.updateBatch(this, coreId, entities);
    }

    @Override
    @Transactional
    public void delete(String coreId, E entity) throws BaseVaselineServerException {
        BaseMultiDaoCrudApiImplHelper.delete(this, coreId, entity);
    }

    @Override
    @Transactional
    public void deleteBatch(String coreId, List<E> entities) throws BaseVaselineServerException {
        BaseMultiDaoCrudApiImplHelper.deleteBatch(this, coreId, entities);
    }

    @Override
    @Transactional
    public void delete(String coreId, Id id) throws BaseVaselineServerException {
        BaseMultiDaoCrudApiImplHelper.delete(this, coreId, id);
    }

    @Override
    public void preSave(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.preSave(entity);
    }

    @Override
    public void postSave(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.postSave(entity);
    }

    @Override
    public void preUpdate(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.preUpdate(entity);
    }

    @Override
    public void postUpdate(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.postUpdate(entity);
    }

    @Override
    public void preDelete(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.preDelete(entity);
    }

    @Override
    public void postDelete(E entity) throws BaseVaselineServerException {
        BaseEntityCrudApiImplHelper.postDelete(entity);
    }

}
