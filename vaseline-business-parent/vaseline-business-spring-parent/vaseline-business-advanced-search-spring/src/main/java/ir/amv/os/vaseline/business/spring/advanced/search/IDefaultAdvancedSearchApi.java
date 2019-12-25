package ir.amv.os.vaseline.business.spring.advanced.search;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.business.spring.basic.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.advanced.search.api.repo.IBaseAdvancedSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    default Page<M> searchBySearchObject(S searchObject, Pageable pagingDto)
            throws BaseBusinessException {
        Page<E> pms = getRepository().searchBySearchObject(searchObject, pagingDto);
        Page<M> result = pms.map(this::convertFrom);
        postGetList(result.getContent());
        return result;
    }
}
