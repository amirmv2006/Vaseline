package ir.amv.os.vaseline.service.apis.advancedsearch.layerimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.apis.advancedsearch.layer.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.apis.advancedsearch.layer.server.IBaseAdvancedSearchService;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedAdvancedSearchService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, SO extends
        IBaseSearchObject, Id extends Serializable>
        extends IBaseImplementedReadOnlyService<E, D, Id>, IBaseAdvancedSearchService<D, SO, Id> {

    IBaseAdvancedSearchApi<E, SO, Id> getAdvancedSearchApi();

    @Override
    default Long countBySearchObject(SO searchObject) throws BaseVaselineClientException {
        try {
            return getAdvancedSearchApi().countBySearchObject(searchObject);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject) throws BaseVaselineClientException {
        try {
            validate(searchObject, validationGroupsForSearch());
            List<E> searchBySearchObject = getAdvancedSearchApi().searchBySearchObject(searchObject);
            return convertEntityToDTO(searchBySearchObject, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchBySearchObject(SO searchObject, PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            validate(searchObject, validationGroupsForSearch());
            List<E> searchBySearchObject = getAdvancedSearchApi().searchBySearchObject(searchObject, pagingDto);
            return convertEntityToDTO(searchBySearchObject, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

}
