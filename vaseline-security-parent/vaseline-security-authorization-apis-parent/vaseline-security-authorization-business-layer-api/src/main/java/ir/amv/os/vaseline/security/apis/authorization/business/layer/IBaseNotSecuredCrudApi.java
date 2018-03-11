package ir.amv.os.vaseline.security.apis.authorization.business.layer;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.annot.NoAuthorization;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseNotSecuredCrudApi<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseCrudApi<E, Id>, IBaseNotSecuredReadOnlyApi<E, Id> {

    @NoAuthorization
    Id saveNotSecured(E entity) throws BaseVaselineServerException;
    @NoAuthorization
    List<Id> saveBatchNotSecured(List<E> entities) throws  BaseVaselineServerException;

    @NoAuthorization
    void updateNotSecured(E entity) throws BaseVaselineServerException;
    @NoAuthorization
    void updateBatchNotSecured(List<E> entities) throws  BaseVaselineServerException;

    @NoAuthorization
    void deleteNotSecured(E entity) throws BaseVaselineServerException;
    @NoAuthorization
    void deleteBatchNotSecured(List<E> entities) throws  BaseVaselineServerException;
    @NoAuthorization
    void deleteNotSecured(Id id) throws BaseVaselineServerException;
}
