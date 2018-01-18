package ir.amv.os.vaseline.ws.common.apis.basic.layerimpl.crud;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.apis.basic.layer.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.crud.IBaseCrudWebService;
import ir.amv.os.vaseline.ws.common.apis.basic.layerimpl.ro.IBaseImplementedReadOnlyWebService;

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
