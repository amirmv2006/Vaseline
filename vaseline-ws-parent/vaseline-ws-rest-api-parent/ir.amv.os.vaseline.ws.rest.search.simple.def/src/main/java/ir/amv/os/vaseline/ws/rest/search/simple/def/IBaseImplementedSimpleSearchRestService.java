package ir.amv.os.vaseline.ws.rest.search.simple.def;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.search.simple.api.server.IBaseSimpleSearchService;
import ir.amv.os.vaseline.ws.common.search.simple.def.IBaseImplementedSimpleSearchWebService;
import ir.amv.os.vaseline.ws.rest.basic.def.ro.IBaseImplementedReadOnlyRestService;
import ir.amv.os.vaseline.ws.rest.search.simple.api.IBaseSimpleSearchRestService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedSimpleSearchRestService<D extends IBaseDto<Id>, Id extends Serializable, Service extends
        IBaseSimpleSearchService<D, Id>>
        extends IBaseSimpleSearchRestService<D, Id>,
        IBaseImplementedReadOnlyRestService<D, Id, Service>,
        IBaseImplementedSimpleSearchWebService<D, Id, Service> {

    @Override
    default Long countByExample(D example) throws BaseVaselineClientException {
        return IBaseImplementedSimpleSearchWebService.super.countByExample(example);
    }

    @Override
    default List<D> searchByExample(D example) throws BaseVaselineClientException {
        return IBaseImplementedSimpleSearchWebService.super.searchByExample(example);
    }

    @Override
    default List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        return IBaseImplementedSimpleSearchWebService.super.searchByExample(example, pagingDto);
    }

}
