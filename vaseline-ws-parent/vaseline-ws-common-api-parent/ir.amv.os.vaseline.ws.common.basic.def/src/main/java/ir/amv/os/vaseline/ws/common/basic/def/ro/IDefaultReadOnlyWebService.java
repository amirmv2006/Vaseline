package ir.amv.os.vaseline.ws.common.basic.def.ro;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;
import ir.amv.os.vaseline.ws.common.basic.def.base.IDefaultWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultReadOnlyWebService<Id extends Serializable, D extends IBaseDto<Id>, Service extends
        IBaseReadOnlyService<Id, D>>
        extends IBaseReadOnlyWebService<Id, D>, IDefaultWebService {

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
