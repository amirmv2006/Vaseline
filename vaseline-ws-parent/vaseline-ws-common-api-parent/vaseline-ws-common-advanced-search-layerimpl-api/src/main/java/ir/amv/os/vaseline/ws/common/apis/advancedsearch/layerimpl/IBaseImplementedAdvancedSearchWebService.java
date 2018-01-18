package ir.amv.os.vaseline.ws.common.apis.advancedsearch.layerimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.apis.advancedsearch.layer.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.ws.common.apis.advancedsearch.layer.IBaseAdvancedSearchWebService;
import ir.amv.os.vaseline.ws.common.apis.basic.layerimpl.ro.IBaseImplementedReadOnlyWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseImplementedAdvancedSearchWebService<D extends IBaseDto<Id>, SO extends IBaseSearchObject, Id
        extends Serializable, Service extends IBaseAdvancedSearchService<D, SO, Id>>
        extends IBaseAdvancedSearchWebService<D, SO, Id>, IBaseImplementedReadOnlyWebService<D, Id, Service> {

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseVaselineClientException {
        return getService().countBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException {
        return getService().searchBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException {
        return getService().searchBySearchObject(searchObject, pagingDto);
    }
}
