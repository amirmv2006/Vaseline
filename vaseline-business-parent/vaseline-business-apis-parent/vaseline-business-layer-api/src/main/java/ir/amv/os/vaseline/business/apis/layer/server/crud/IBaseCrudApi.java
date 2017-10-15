package ir.amv.os.vaseline.business.apis.layer.server.crud;

import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

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
