package ir.amv.os.vaseline.ws.common.basic.api.ro;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.basic.api.base.IBaseWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseReadOnlyWebService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseWebService {

    D getById(Id id) throws BaseVaselineClientException;

    Long countAll() throws BaseVaselineClientException;
    List<D> getAll() throws BaseVaselineClientException;
    List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException;
}
