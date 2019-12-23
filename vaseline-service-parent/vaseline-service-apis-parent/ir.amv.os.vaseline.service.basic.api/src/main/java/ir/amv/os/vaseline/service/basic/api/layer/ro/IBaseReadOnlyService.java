package ir.amv.os.vaseline.service.basic.api.server.ro;

import ir.amv.os.vaseline.service.basic.api.server.base.IBaseService;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyService<I extends Serializable, D extends IBaseDto<I>>
        extends IBaseService {

    D getById(I id) throws BaseExternalException;

    Long countAll() throws BaseExternalException;
    List<D> getAll() throws BaseExternalException;
    List<D> getAll(PagingDto pagingDto) throws BaseExternalException;


}
