package ir.amv.os.vaseline.ws.common.basic.def.crud;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.ws.common.basic.api.crud.IBaseCrudWebService;
import ir.amv.os.vaseline.ws.common.basic.def.ro.IDefaultReadOnlyWebService;

import java.io.Serializable;

/**
 * @author Amir
 */
public interface IDefaultCrudWebService<Id extends Serializable, D extends IBaseDto<Id>, Service extends
        IBaseCrudService<Id, D>>
        extends IBaseCrudWebService<Id, D>, IDefaultReadOnlyWebService<Id, D, Service> {

    @Override
    default Id save(D t) throws BaseExternalException {
        return getService().save(t);
    }

    @Override
    default void update(D t) throws BaseExternalException {
        getService().update(t);
    }

    @Override
    default void delete(D id) throws BaseExternalException {
        getService().delete(id);
    }

    @Override
    default void deleteById(Id id) throws BaseExternalException {
        getService().deleteById(id);
    }
}
