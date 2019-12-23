package ir.amv.os.vaseline.ws.rest.server.base.ro;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.ws.rest.server.base.parent.IBaseRestService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/13/2016.
 */
public interface IBaseReadOnlyRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseRestService {

    D getById(Id id) throws BaseExternalException;

    Long countAll() throws BaseExternalException;

    List<D> getAll() throws BaseExternalException;

    List<D> getAll(PagingDto pagingDto) throws BaseExternalException;


}
