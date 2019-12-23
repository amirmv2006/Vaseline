package ir.amv.os.vaseline.ws.rest.basic.def.ro;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.ws.common.basic.def.ro.IDefaultReadOnlyWebService;
import ir.amv.os.vaseline.ws.rest.basic.api.ro.IBaseReadOnlyRestService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultReadOnlyRestService<Id extends Serializable, D extends IBaseDto<Id>, Service extends
        IBaseReadOnlyService<Id, D>>
        extends IBaseReadOnlyRestService<Id, D>, IDefaultReadOnlyWebService<Id, D, Service> {

    @Override
    default D getById(Id id) throws BaseExternalException {
        return IDefaultReadOnlyWebService.super.getById(id);
    }

    @Override
    default Long countAll() throws BaseExternalException {
        return IDefaultReadOnlyWebService.super.countAll();
    }

    @Override
    default List<D> getAll() throws BaseExternalException {
        return IDefaultReadOnlyWebService.super.getAll();
    }

    @Override
    default List<D> getAll(PagingDto pagingDto) throws BaseExternalException {
        return IDefaultReadOnlyWebService.super.getAll(pagingDto);
    }
}
