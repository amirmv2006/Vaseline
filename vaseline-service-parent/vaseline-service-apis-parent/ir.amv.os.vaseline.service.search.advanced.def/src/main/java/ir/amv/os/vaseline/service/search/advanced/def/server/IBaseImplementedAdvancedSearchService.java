package ir.amv.os.vaseline.service.search.advanced.def.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.service.basic.def.server.ro.IBaseImplementedReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedAdvancedSearchService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, SO extends
        IBaseSearchObject, Id extends Serializable, Api extends IBaseAdvancedSearchApi<E, SO, Id>>
        extends IBaseImplementedReadOnlyService<E, D, Id, Api>, IBaseAdvancedSearchService<D, SO, Id> {

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseVaselineClientException {
        try {
            return getApiProxy().countBySearchObject(searchObject);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException {
        try {
            validate(searchObject, validationGroupsForSearch());
            List<E> searchBySearchObject = getApiProxy().searchBySearchObject(searchObject);
            return convertEntityToDTO(searchBySearchObject, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            validate(searchObject, validationGroupsForSearch());
            List<E> searchBySearchObject = getApiProxy().searchBySearchObject(searchObject, pagingDto);
            return convertEntityToDTO(searchBySearchObject, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

}
