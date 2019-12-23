package ir.amv.os.vaseline.ws.rest.server.base.ro.impl;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.ws.rest.server.base.parent.impl.BaseRestServiceImpl;
import ir.amv.os.vaseline.ws.rest.server.base.ro.IBaseReadOnlyRestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReadOnlyRestServiceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends IBaseReadOnlyService<Id, D>>
        extends BaseRestServiceImpl
        implements IBaseReadOnlyRestService<D, Id>{

    protected S service;

    @Override
    public D getById(Id id) throws BaseExternalException {
        return service.getById(id);
    }

    @Override
    public Long countAll() throws BaseExternalException {
        return service.countAll();
    }

    @Override
    public List<D> getAll() throws BaseExternalException {
        return service.getAll();
    }

    @Override
    public List<D> getAll(PagingDto pagingDto) throws BaseExternalException {
        return service.getAll(pagingDto);
    }

    @Autowired
    public void setService(S service) {
        this.service = service;
    }
}
