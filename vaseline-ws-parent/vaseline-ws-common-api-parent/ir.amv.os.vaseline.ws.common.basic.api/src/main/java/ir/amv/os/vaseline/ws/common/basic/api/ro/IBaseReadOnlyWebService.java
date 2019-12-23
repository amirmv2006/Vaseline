package ir.amv.os.vaseline.ws.common.basic.api.ro;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.ws.common.basic.api.base.IBaseWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseReadOnlyWebService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseWebService {

    D getById(Id id) throws BaseExternalException;

    Long countAll() throws BaseExternalException;
    List<D> getAll() throws BaseExternalException;
    List<D> getAll(PagingDto pagingDto) throws BaseExternalException;
}
