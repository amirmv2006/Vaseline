package ir.amv.os.vaseline.ws.common.basic.def.crud;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.basic.api.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.ws.common.basic.api.crud.IBaseCrudWebService;
import ir.amv.os.vaseline.ws.common.basic.def.ro.IBaseImplementedReadOnlyWebService;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IBaseImplementedCrudWebService<D extends IBaseDto<Id>, Id extends Serializable, Service extends
        IBaseCrudService<D, Id>>
        extends IBaseCrudWebService<D, Id>, IBaseImplementedReadOnlyWebService<D, Id, Service> {

    @Override
    default Id save(D t) throws BaseVaselineClientException {
        return getService().save(t);
    }

    @Override
    default void update(D t) throws BaseVaselineClientException {
        getService().update(t);
    }

    @Override
    default void delete(D id) throws BaseVaselineClientException {
        getService().delete(id);
    }

    @Override
    default void deleteById(Id id) throws BaseVaselineClientException {
        getService().deleteById(id);
    }
}
