package ir.amv.os.vaseline.security.authorization.business.api;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.annot.NoAuthorization;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseNotSecuredCrudApi<E extends IBaseBusinessModel<Id>, Id extends Serializable>
        extends IBaseCrudApi<Id, E>, IBaseNotSecuredReadOnlyApi<E, Id> {

    @NoAuthorization
    Id saveNotSecured(E entity) throws BaseBusinessException;
    @NoAuthorization
    List<Id> saveBatchNotSecured(List<E> entities) throws BaseBusinessException;

    @NoAuthorization
    void updateNotSecured(E entity) throws BaseBusinessException;
    @NoAuthorization
    void updateBatchNotSecured(List<E> entities) throws BaseBusinessException;

    @NoAuthorization
    void deleteNotSecured(E entity) throws BaseBusinessException;
    @NoAuthorization
    void deleteBatchNotSecured(List<E> entities) throws BaseBusinessException;
    @NoAuthorization
    void deleteNotSecured(Id id) throws BaseBusinessException;
}
