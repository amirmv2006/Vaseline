package ir.amv.os.vaseline.ws.common.apis.basic.layer.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.apis.basic.layer.base.IBaseWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseReadOnlyWebService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseWebService {

    D getById(Id id) throws BaseVaselineClientException;

    Long countAll() throws BaseVaselineClientException;
    List<D> getAll() throws BaseVaselineClientException;
    List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException;
}
