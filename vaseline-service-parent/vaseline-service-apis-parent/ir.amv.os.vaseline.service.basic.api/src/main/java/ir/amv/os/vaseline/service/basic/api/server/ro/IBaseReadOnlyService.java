package ir.amv.os.vaseline.service.basic.api.server.ro;

import ir.amv.os.vaseline.service.basic.api.server.base.IBaseService;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyService<I extends Serializable, D extends IBaseDto<I>>
        extends IBaseService {

    D getById(I id) throws BaseVaselineClientException;

    Long countAll() throws BaseVaselineClientException;
    List<D> getAll() throws BaseVaselineClientException;
    List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException;


}
