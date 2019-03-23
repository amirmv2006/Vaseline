package ir.amv.os.vaseline.ws.rest.basic.def.crud;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.basic.api.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.ws.common.basic.def.crud.IDefaultCrudWebService;
import ir.amv.os.vaseline.ws.rest.basic.api.crud.IBaseCrudRestService;
import ir.amv.os.vaseline.ws.rest.basic.def.ro.IDefaultReadOnlyRestService;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IDefaultCrudRestService<D extends IBaseDto<Id>, Id extends Serializable, Service extends
        IBaseCrudService<D, Id>>
        extends IBaseCrudRestService<D, Id>,
        IDefaultReadOnlyRestService<D, Id, Service>,
        IDefaultCrudWebService<D, Id, Service> {

    @Override
    default Id save(D t) throws BaseVaselineClientException {
        return IDefaultCrudWebService.super.save(t);
    }

    @Override
    default void update(D t) throws BaseVaselineClientException {
        IDefaultCrudWebService.super.update(t);
    }

    @Override
    default void delete(D id) throws BaseVaselineClientException {
        IDefaultCrudWebService.super.delete(id);
    }

    @Override
    default void deleteById(Id id) throws BaseVaselineClientException {
        IDefaultCrudWebService.super.deleteById(id);
    }
}
