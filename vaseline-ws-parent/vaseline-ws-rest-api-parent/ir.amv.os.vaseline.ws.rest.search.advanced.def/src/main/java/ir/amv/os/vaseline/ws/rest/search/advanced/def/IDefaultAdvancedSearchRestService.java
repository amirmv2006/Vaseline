package ir.amv.os.vaseline.ws.rest.search.advanced.def;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
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
public interface IDefaultAdvancedSearchRestService<D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id
        extends Serializable, Service extends IBaseAdvancedSearchService<D, SO, Id>>
        extends IBaseAdvancedSearchRestService<D, SO, Id>,
        IDefaultReadOnlyRestService<D, Id, Service>,
        IDefaultAdvancedSearchWebService<D, SO, Id, Service> {

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseVaselineClientException {
        return IDefaultAdvancedSearchWebService.super.countBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException {
        return IDefaultAdvancedSearchWebService.super.searchBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException {
        return IDefaultAdvancedSearchWebService.super.searchBySearchObject(searchObject, pagingDto);
    }

}
