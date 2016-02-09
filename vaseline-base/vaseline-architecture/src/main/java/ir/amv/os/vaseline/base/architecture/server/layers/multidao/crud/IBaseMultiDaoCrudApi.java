package ir.amv.os.vaseline.base.architecture.server.layers.multidao.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.api.IBaseEntityCrudApi;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseMultiDaoCrudApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseMultiDaoReadOnlyApi<E, D, Id>, IBaseEntityCrudApi<E> {

    Id save(String coreId, E entity) throws BaseVaselineServerException;
    List<Id> saveBatch(String coreId, List<E> entities) throws  BaseVaselineServerException;

    void update(String coreId, E entity) throws BaseVaselineServerException;
    void updateBatch(String coreId, List<E> entities) throws  BaseVaselineServerException;

    void delete(String coreId, E entity) throws BaseVaselineServerException;
    void deleteBatch(String coreId, List<E> entities) throws  BaseVaselineServerException;
    void delete(String coreId, Id id) throws BaseVaselineServerException;

    @Override
    IBaseCrudDao getDaoFor(String coreId) throws BaseVaselineServerException;
}
