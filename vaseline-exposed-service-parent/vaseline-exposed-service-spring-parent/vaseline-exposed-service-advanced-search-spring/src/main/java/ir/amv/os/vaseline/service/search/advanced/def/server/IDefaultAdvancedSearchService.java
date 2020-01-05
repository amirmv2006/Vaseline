package ir.amv.os.vaseline.service.search.advanced.def.server;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultReadOnlyService;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IDefaultAdvancedSearchService<
            I extends Serializable,
            D extends IBaseDto<I>,
            M extends IBaseBusinessEntity<I, M>,
            S extends IBaseSearchObject,
            A extends IBaseAdvancedSearchApi<I, M, S>
        >
        extends IDefaultReadOnlyService<I, D, M, A>, IBaseAdvancedSearchService<I, D, S> {

    @Override
    default Long countBySearchObject(S searchObject) throws BaseExternalException {
        return handleExceptions(() -> getApi().countBySearchObject(searchObject));
    }

    @Override
    default List<D> searchBySearchObject(S searchObject) throws BaseExternalException {
        return handleExceptions(() -> {
            validate(searchObject, validationGroupsForSearch());
            List<M> searchBySearchObject = getApi().searchBySearchObject(searchObject);
            return convertFrom(searchBySearchObject, validationGroupsForShow());
        });
    }

    @Override
    default Page<D> searchBySearchObject(S searchObject, Pageable pagingDto) throws BaseExternalException {
        return handleExceptions(() -> {
            validate(searchObject, validationGroupsForSearch());
            Page<M> searchBySearchObject = getApi().searchBySearchObject(searchObject, pagingDto);
            return convertFrom(searchBySearchObject, validationGroupsForShow());
        });
    }

}
