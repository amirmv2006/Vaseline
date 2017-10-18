package ir.amv.os.vaseline.service.apis.simplesearch.layerimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.apis.simplesearch.layer.server.IBaseSimpleSearchApi;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyService;
import ir.amv.os.vaseline.service.apis.simplesearch.layer.server.IBaseSimpleSearchService;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedSimpleSearchService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Api extends IBaseSimpleSearchApi<E, D, Id>>
        extends IBaseSimpleSearchService<D, Id>, IBaseImplementedReadOnlyService<E, D, Id, Api> {

    @Override
    default Long countByExample(D example) throws BaseVaselineClientException {
        try {
            return getApi().countByExample(example);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchByExample(D example) throws BaseVaselineClientException {
        try {
            validate(example, validationGroupsForSearch());
            List<E> searchByExample = getApi().searchByExample(example);
            return convertEntityToDTO(searchByExample, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            validate(example, validationGroupsForSearch());
            List<E> searchByExample = getApi().searchByExample(example, pagingDto);
            return convertEntityToDTO(searchByExample, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
