package ir.amv.os.vaseline.ws.common.basic.def.ro;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
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
    default D getById(Id id) throws BaseExternalException {
        return getService().getById(id);
    }

    @Override
    default Long countAll() throws BaseExternalException {
        return getService().countAll();
    }

    @Override
    default List<D> getAll() throws BaseExternalException {
        return getService().getAll();
    }

    @Override
    default List<D> getAll(PagingDto pagingDto) throws BaseExternalException {
        return getService().getAll(pagingDto);
    }
}
