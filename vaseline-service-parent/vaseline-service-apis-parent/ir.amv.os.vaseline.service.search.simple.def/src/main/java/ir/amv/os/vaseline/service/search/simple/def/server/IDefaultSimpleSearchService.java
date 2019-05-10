package ir.amv.os.vaseline.service.search.simple.def.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.search.simple.api.server.IBaseSimpleSearchApi;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultReadOnlyService;
import ir.amv.os.vaseline.service.search.simple.api.server.IBaseSimpleSearchService;

import java.io.Serializable;
import java.util.List;

public interface IDefaultSimpleSearchService<Id extends
        Serializable, E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Api extends IBaseSimpleSearchApi<Id, E, D>>
        extends IBaseSimpleSearchService<Id, D>, IDefaultReadOnlyService<Id, E, D, Api> {

    @Override
    default Long countByExample(D example) throws BaseVaselineClientException {
        try {
            return getApiProxy().countByExample(example);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchByExample(D example) throws BaseVaselineClientException {
        try {
            validate(example, validationGroupsForSearch());
            List<E> searchByExample = getApiProxy().searchByExample(example);
            return convertEntityToDTO(searchByExample, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            validate(example, validationGroupsForSearch());
            List<E> searchByExample = getApiProxy().searchByExample(example, pagingDto);
            return convertEntityToDTO(searchByExample, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
