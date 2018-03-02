package ir.amv.os.vaseline.ws.rest.apis.advancedsearch.layerimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.apis.advancedsearch.layer.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.ws.common.apis.advancedsearch.layerimpl.IBaseImplementedAdvancedSearchWebService;
import ir.amv.os.vaseline.ws.rest.apis.advancedsearch.layer.IBaseAdvancedSearchRestService;
import ir.amv.os.vaseline.ws.rest.apis.basic.layerimpl.ro.IBaseImplementedReadOnlyRestService;

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
