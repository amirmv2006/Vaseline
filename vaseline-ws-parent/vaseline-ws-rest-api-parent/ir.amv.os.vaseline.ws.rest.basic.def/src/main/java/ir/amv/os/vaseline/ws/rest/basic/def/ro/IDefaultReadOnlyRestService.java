package ir.amv.os.vaseline.ws.rest.basic.def.ro;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.ws.common.basic.def.ro.IDefaultReadOnlyWebService;
import ir.amv.os.vaseline.ws.rest.basic.api.ro.IBaseReadOnlyRestService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultReadOnlyRestService<D extends IBaseDto<Id>, Id extends Serializable, Service extends
        IBaseReadOnlyService<D, Id>>
        extends IBaseReadOnlyRestService<D, Id>, IDefaultReadOnlyWebService<D, Id, Service> {

    @Override
    default D getById(Id id) throws BaseVaselineClientException {
        return IDefaultReadOnlyWebService.super.getById(id);
    }

    @Override
    default Long countAll() throws BaseVaselineClientException {
        return IDefaultReadOnlyWebService.super.countAll();
    }

    @Override
    default List<D> getAll() throws BaseVaselineClientException {
        return IDefaultReadOnlyWebService.super.getAll();
    }

    @Override
    default List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException {
        return IDefaultReadOnlyWebService.super.getAll(pagingDto);
    }
}
