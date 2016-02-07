package ir.amv.os.vaseline.base.architecture.impl.server.layers.crud.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ro.api.BaseReadOnlyApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.architecture.server.layers.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
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
        preSave(entity);
        Id id = dao.save(entity);
        postSave(entity);
        return id;
    }

    @Override
    @Transactional
    public List<Id> saveBatch(List<E> entities) throws BaseVaselineServerException {
        List<Id> result = new ArrayList<Id>();
        if (entities != null) {
            for (E entity : entities) {
                Id id = save(entity);
                result.add(id);
            }
        }
        return result;
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
        preUpdate(entity);
        dao.update(entity);
        postUpdate(entity);
    }

    @Override
    @Transactional
    public void updateBatch(List<E> entities) throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                update(entity);
            }
        }
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
        preDelete(entity);
        dao.delete(entity);
        postDelete(entity);
    }

    @Override
    @Transactional
    public void deleteBatch(List<E> entities) throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                delete(entity);
            }
        }
    }

    @Override
    @Transactional
    public void delete(Id id) throws BaseVaselineServerException {
        E byId = getById(id);
        delete(byId);
    }

    @Override
    public void preDelete(E entity) throws BaseVaselineServerException {

    }

    @Override
    public void postDelete(E entity) throws BaseVaselineServerException {

    }
}
