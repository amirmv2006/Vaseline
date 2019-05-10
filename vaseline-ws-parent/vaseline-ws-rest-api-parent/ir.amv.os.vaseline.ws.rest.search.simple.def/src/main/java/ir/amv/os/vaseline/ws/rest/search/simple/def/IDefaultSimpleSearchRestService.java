package ir.amv.os.vaseline.ws.rest.search.simple.def;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.search.simple.api.server.IBaseSimpleSearchService;
import ir.amv.os.vaseline.ws.common.search.simple.def.IDefaultSimpleSearchWebService;
import ir.amv.os.vaseline.ws.rest.basic.def.ro.IDefaultReadOnlyRestService;
import ir.amv.os.vaseline.ws.rest.search.simple.api.IBaseSimpleSearchRestService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultSimpleSearchRestService<Id extends Serializable, D extends IBaseDto<Id>, Service extends
        IBaseSimpleSearchService<Id, D>>
        extends IBaseSimpleSearchRestService<Id, D>,
        IDefaultReadOnlyRestService<Id, D, Service>,
        IDefaultSimpleSearchWebService<Id, D, Service> {

    @Override
    default Long countByExample(D example) throws BaseVaselineClientException {
        return IDefaultSimpleSearchWebService.super.countByExample(example);
    }

    @Override
    default List<D> searchByExample(D example) throws BaseVaselineClientException {
        return IDefaultSimpleSearchWebService.super.searchByExample(example);
    }

    @Override
    default List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        return IDefaultSimpleSearchWebService.super.searchByExample(example, pagingDto);
    }

}
