package ir.amv.os.vaseline.base.architecture.impl.server.layers.crud.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ro.api.BaseReadOnlyApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.architecture.server.layers.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseCrudDao<E, D, Id>>
        extends BaseReadOnlyApiImpl<E, D, Id, DAO>
        implements IBaseCrudApi<E, D, Id> {

    @Override
    @Transactional
    public Id save(E entity) throws BaseVaselineServerException {
        return BaseCrudApiImplHelper.save(this, getDao(), entity);
    }

    @Override
    @Transactional
    public List<Id> saveBatch(List<E> entities) throws BaseVaselineServerException {
        return BaseCrudApiImplHelper.saveBatch(this, getDao(), entities);
    }

    @Override
    public void preSave(E entity) throws BaseVaselineServerException {
    }

    @Override
    public void postSave(E entity) throws BaseVaselineServerException {
    }

    @Override
    @Transactional
    public void update(E entity) throws BaseVaselineServerException {
        BaseCrudApiImplHelper.update(this, getDao(), entity);
    }

    @Override
    @Transactional
    public void updateBatch(List<E> entities) throws BaseVaselineServerException {
        BaseCrudApiImplHelper.updateBatch(this, getDao(), entities);
    }

    @Override
    public void preUpdate(E entity) throws BaseVaselineServerException {
    }

    @Override
    public void postUpdate(E entity) throws BaseVaselineServerException {
    }

    @Override
    @Transactional
    public void delete(E entity) throws BaseVaselineServerException {
        BaseCrudApiImplHelper.delete(this, getDao(), entity);
    }

    @Override
    @Transactional
    public void deleteBatch(List<E> entities) throws BaseVaselineServerException {
        BaseCrudApiImplHelper.deleteBatch(this, getDao(), entities);
    }

    @Override
    @Transactional
    public void delete(Id id) throws BaseVaselineServerException {
        BaseCrudApiImplHelper.delete(this, getDao(), id);
    }

    @Override
    public void preDelete(E entity) throws BaseVaselineServerException {
    }

    @Override
    public void postDelete(E entity) throws BaseVaselineServerException {
    }
}
