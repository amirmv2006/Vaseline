package ir.amv.os.vaseline.security.authorization.api.shared.base.api.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.security.authorization.api.shared.base.api.ro.IBaseSecuredReadOnlyApi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredCrudApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseCrudApi<E, D, Id>, IBaseSecuredReadOnlyApi<E, D, Id> {

    String BASE_OP_CREATE = "create";
    String BASE_OP_UPDATE = "update";
    String BASE_OP_DELETE = "delete";

    Id saveNotSecured(E entity) throws BaseVaselineServerException;
    List<Id> saveBatchNotSecured(List<E> entities) throws  BaseVaselineServerException;

    void updateNotSecured(E entity) throws BaseVaselineServerException;
    void updateBatchNotSecured(List<E> entities) throws  BaseVaselineServerException;

    void deleteNotSecured(E entity) throws BaseVaselineServerException;
    void deleteBatchNotSecured(List<E> entities) throws  BaseVaselineServerException;
    void deleteNotSecured(Id id) throws BaseVaselineServerException;

    String getCreateOperationTreeName();
    String getUpdateOperationTreeName();
    String getDeleteOperationTreeName();
}
