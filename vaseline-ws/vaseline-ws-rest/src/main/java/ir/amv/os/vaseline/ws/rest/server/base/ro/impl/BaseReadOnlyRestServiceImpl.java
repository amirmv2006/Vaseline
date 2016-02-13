package ir.amv.os.vaseline.ws.rest.server.base.ro.impl;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service.IBaseReadOnlyService;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.rest.server.base.parent.impl.BaseRestServiceImpl;
import ir.amv.os.vaseline.ws.rest.server.base.ro.IBaseReadOnlyRestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReadOnlyRestServiceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends IBaseReadOnlyService<D, Id>>
        extends BaseRestServiceImpl
        implements IBaseReadOnlyRestService<D, Id>{

    protected S service;

    @Override
    public D getById(Id id) throws BaseVaselineClientException {
        return service.getById(id);
    }

    @Override
    public Long countAll() throws BaseVaselineClientException {
        return service.countAll();
    }

    @Override
    public List<D> getAll() throws BaseVaselineClientException {
        return service.getAll();
    }

    @Override
    public List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException {
        return service.getAll(pagingDto);
    }

    @Override
    public Long countByExample(D example) throws BaseVaselineClientException {
        return service.countByExample(example);
    }

    @Override
    public List<D> searchByExample(D example) throws BaseVaselineClientException {
        return service.searchByExample(example);
    }

    @Override
    public List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        return service.searchByExample(example, pagingDto);
    }

    @Autowired
    public void setService(S service) {
        this.service = service;
    }
}
