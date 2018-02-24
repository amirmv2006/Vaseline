package ir.amv.os.vaseline.business.apis.basic.layer.server.crud;

import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudApi<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<E, Id>, IBaseEntityCrudApi<E> {

    Id save(E entity) throws BaseVaselineServerException;
    List<Id> saveBatch(List<E> entities) throws  BaseVaselineServerException;

    void update(E entity) throws BaseVaselineServerException;
    void updateBatch(List<E> entities) throws  BaseVaselineServerException;

    void delete(E entity) throws BaseVaselineServerException;
    void deleteBatch(List<E> entities) throws  BaseVaselineServerException;
    void delete(Id id) throws BaseVaselineServerException;

}
