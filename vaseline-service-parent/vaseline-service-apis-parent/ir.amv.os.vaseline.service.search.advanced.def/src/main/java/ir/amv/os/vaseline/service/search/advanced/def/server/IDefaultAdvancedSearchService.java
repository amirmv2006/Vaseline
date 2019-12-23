package ir.amv.os.vaseline.service.search.advanced.def.server;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IDefaultAdvancedSearchService<Id extends Serializable, E extends IBaseBusinessModel<Id>, D extends IBaseDto<Id>, SO extends
        IBaseSearchObject, Api extends IBaseAdvancedSearchApi<Id, E, SO>>
        extends IDefaultReadOnlyService<Id, E, D, Api>, IBaseAdvancedSearchService<Id, D, SO> {

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseExternalException {
        try {
            return getApiProxy().countBySearchObject(searchObject);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseExternalException {
        try {
            validate(searchObject, validationGroupsForSearch());
            List<E> searchBySearchObject = getApiProxy().searchBySearchObject(searchObject);
            return convertEntityToDTO(searchBySearchObject, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseExternalException {
        try {
            validate(searchObject, validationGroupsForSearch());
            List<E> searchBySearchObject = getApiProxy().searchBySearchObject(searchObject, pagingDto);
            return convertEntityToDTO(searchBySearchObject, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

}
