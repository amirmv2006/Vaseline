package ir.amv.os.vaseline.ws.rest.search.advanced.def;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.ws.common.search.advanced.def.IDefaultAdvancedSearchWebService;
import ir.amv.os.vaseline.ws.rest.search.advanced.api.IBaseAdvancedSearchRestService;
import ir.amv.os.vaseline.ws.rest.basic.def.ro.IDefaultReadOnlyRestService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultAdvancedSearchRestService<Id
        extends Serializable, D extends IBaseDto<Id>, SO extends IBaseSearchObject, Service extends IBaseAdvancedSearchService<Id, D, SO>>
        extends IBaseAdvancedSearchRestService<Id, D, SO>,
        IDefaultReadOnlyRestService<Id, D, Service>,
        IDefaultAdvancedSearchWebService<Id, D, SO, Service> {

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseExternalException {
        return IDefaultAdvancedSearchWebService.super.countBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseExternalException {
        return IDefaultAdvancedSearchWebService.super.searchBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseExternalException {
        return IDefaultAdvancedSearchWebService.super.searchBySearchObject(searchObject, pagingDto);
    }

}
