package ir.amv.os.vaseline.ws.common.apis.basic.layerimpl.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.apis.basic.layer.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.ro.IBaseReadOnlyWebService;
import ir.amv.os.vaseline.ws.common.apis.basic.layerimpl.base.IBaseImplementedWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedReadOnlyWebService<D extends IBaseDto<Id>, Id extends Serializable, Service extends
        IBaseReadOnlyService<D, Id>>
        extends IBaseReadOnlyWebService<D, Id>, IBaseImplementedWebService {

    Service getService();

    @Override
    default D getById(Id id) throws BaseVaselineClientException {
        return getService().getById(id);
    }

    @Override
    default Long countAll() throws BaseVaselineClientException {
        return getService().countAll();
    }

    @Override
    default List<D> getAll() throws BaseVaselineClientException {
        return getService().getAll();
    }

    @Override
    default List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException {
        return getService().getAll(pagingDto);
    }
}
