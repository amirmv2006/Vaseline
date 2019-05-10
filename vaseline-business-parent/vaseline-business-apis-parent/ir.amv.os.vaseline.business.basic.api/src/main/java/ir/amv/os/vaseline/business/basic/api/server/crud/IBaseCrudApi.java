package ir.amv.os.vaseline.business.basic.api.server.crud;

import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseCrudApi<I extends Serializable, E extends IBaseEntity<I>>
        extends IBaseReadOnlyApi<I, E>, IBaseEntityCrudApi<E> {

    I save(E entity) throws BaseVaselineServerException;
    List<I> saveBatch(List<E> entities) throws  BaseVaselineServerException;

    void update(E entity) throws BaseVaselineServerException;
    void updateBatch(List<E> entities) throws  BaseVaselineServerException;

    void delete(E entity) throws BaseVaselineServerException;
    void deleteBatch(List<E> entities) throws  BaseVaselineServerException;
    void delete(I id) throws BaseVaselineServerException;

}
