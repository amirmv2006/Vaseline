package ir.amv.os.vaseline.ws.rest.basic.def.crud;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.ws.common.basic.def.crud.IDefaultCrudWebService;
import ir.amv.os.vaseline.ws.rest.basic.api.crud.IBaseCrudRestService;
import ir.amv.os.vaseline.ws.rest.basic.def.ro.IDefaultReadOnlyRestService;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IDefaultCrudRestService<Id extends Serializable, D extends IBaseDto<Id>, Service extends
        IBaseCrudService<Id, D>>
        extends IBaseCrudRestService<Id, D>,
        IDefaultReadOnlyRestService<Id, D, Service>,
        IDefaultCrudWebService<Id, D, Service> {

    @Override
    default Id save(D t) throws BaseExternalException {
        return IDefaultCrudWebService.super.save(t);
    }

    @Override
    default void update(D t) throws BaseExternalException {
        IDefaultCrudWebService.super.update(t);
    }

    @Override
    default void delete(D id) throws BaseExternalException {
        IDefaultCrudWebService.super.delete(id);
    }

    @Override
    default void deleteById(Id id) throws BaseExternalException {
        IDefaultCrudWebService.super.deleteById(id);
    }
}
