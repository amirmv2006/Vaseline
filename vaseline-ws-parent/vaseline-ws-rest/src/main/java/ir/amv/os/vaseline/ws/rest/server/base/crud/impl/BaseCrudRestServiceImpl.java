package ir.amv.os.vaseline.ws.rest.server.base.crud.impl;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.apis.basic.layer.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.ws.rest.server.base.crud.IBaseCrudRestService;
import ir.amv.os.vaseline.ws.rest.server.base.ro.impl.BaseReadOnlyRestServiceImpl;

import java.io.Serializable;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseCrudRestServiceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends IBaseCrudService<D, Id>>
        extends BaseReadOnlyRestServiceImpl<D, Id, S>
        implements IBaseCrudRestService<D, Id> {

    @Override
    public Id save(D t) throws BaseVaselineClientException {
        return service.save(t);
    }

    @Override
    public void update(D t) throws BaseVaselineClientException {
        service.update(t);
    }

    @Override
    public void delete(D id) throws BaseVaselineClientException {
        service.delete(id);
    }

    @Override
    public void deleteById(Id id) throws BaseVaselineClientException {
        service.deleteById(id);
    }
}
