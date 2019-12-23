package ir.amv.os.vaseline.ws.common.search.advanced.def;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.ws.common.search.advanced.api.IBaseAdvancedSearchWebService;
import ir.amv.os.vaseline.ws.common.basic.def.ro.IDefaultReadOnlyWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultAdvancedSearchWebService<Id
        extends Serializable, D extends IBaseDto<Id>, SO extends IBaseSearchObject, Service extends IBaseAdvancedSearchService<Id, D, SO>>
        extends IBaseAdvancedSearchWebService<Id, D, SO>, IDefaultReadOnlyWebService<Id, D, Service> {

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseExternalException {
        return getService().countBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseExternalException {
        return getService().searchBySearchObject(searchObject);
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseExternalException {
        return getService().searchBySearchObject(searchObject, pagingDto);
    }
}
