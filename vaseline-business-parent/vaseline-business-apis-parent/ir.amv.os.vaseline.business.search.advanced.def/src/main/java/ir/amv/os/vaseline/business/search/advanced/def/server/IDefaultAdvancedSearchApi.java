package ir.amv.os.vaseline.business.search.advanced.def.server;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface IDefaultAdvancedSearchApi<
        I extends Serializable,
        M extends IBaseBusinessModel<I>,
        E extends IBasePersistenceModel<I>,
        S extends IBaseSearchObject,
        R extends IBaseAdvancedSearchRepository<I, E, S>>
        extends IBaseAdvancedSearchApi<I, M, S>, IDefaultReadOnlyApi<I, M, E, R> {

    @Transactional
    default Long countBySearchObject(S searchObject) throws BaseBusinessException {
        return getRepository().countBySearchObject(searchObject);
    }

    @Transactional
    default List<M> searchBySearchObject(S searchObject) throws BaseBusinessException {
        List<E> pms = getRepository().searchBySearchObject(searchObject);
        List<M> result = new ArrayList<>();
        pms.forEach(pm -> result.add(convertFrom(pm)));
        postGetList(result);
        return result;
    }

    @Transactional
    default List<M> searchBySearchObject(S searchObject, PagingDto pagingDto)
            throws BaseBusinessException {
        List<E> pms = getRepository().searchBySearchObject(searchObject, pagingDto);
        List<M> result = new ArrayList<>();
        pms.forEach(pm -> result.add(convertFrom(pm)));
        postGetList(result);
        return result;
    }
}
