package ir.amv.os.vaseline.ws.rest.search.advanced.def;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.ws.common.search.advanced.def.IBaseImplementedAdvancedSearchWebService;
import ir.amv.os.vaseline.ws.rest.search.advanced.api.IBaseAdvancedSearchRestService;
import ir.amv.os.vaseline.ws.rest.basic.def.ro.IBaseImplementedReadOnlyRestService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedAdvancedSearchRestService<D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id
        extends Serializable, Service extends IBaseAdvancedSearchService<D, SO, Id>>
        extends IBaseAdvancedSearchRestService<D, SO, Id>,
        IBaseImplementedReadOnlyRestService<D, Id, Service>,
        IBaseImplementedAdvancedSearchWebService<D, SO, Id, Service> {

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseVaselineClientException {
        return IBaseImplementedAdvancedSearchWebService.super.countBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException {
        return IBaseImplementedAdvancedSearchWebService.super.searchBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException {
        return IBaseImplementedAdvancedSearchWebService.super.searchBySearchObject(searchObject, pagingDto);
    }

}
