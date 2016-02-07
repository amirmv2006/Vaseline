package ir.amv.os.vaseline.base.architecture.server.layers.crud.api;

import ir.amv.os.vaseline.base.architecture.server.layers.ro.api.IBaseReadOnlyApi;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<E, D, Id> {

    Id save(E entity) throws BaseVaselineServerException;
    List<Id> saveBatch(List<E> entities) throws  BaseVaselineServerException;
    void preSave(E entity) throws BaseVaselineServerException;
    void postSave(E entity) throws BaseVaselineServerException;

    void update(E entity) throws BaseVaselineServerException;
    void updateBatch(List<E> entities) throws  BaseVaselineServerException;
    void preUpdate(E entity) throws BaseVaselineServerException;
    void postUpdate(E entity) throws BaseVaselineServerException;

    void delete(E entity) throws BaseVaselineServerException;
    void deleteBatch(List<E> entities) throws  BaseVaselineServerException;
    void delete(Id id) throws BaseVaselineServerException;
    void preDelete(E entity) throws BaseVaselineServerException;
    void postDelete(E entity) throws BaseVaselineServerException;

}
