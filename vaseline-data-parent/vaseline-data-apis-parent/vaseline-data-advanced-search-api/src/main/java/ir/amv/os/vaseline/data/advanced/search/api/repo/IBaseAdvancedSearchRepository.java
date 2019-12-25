package ir.amv.os.vaseline.data.advanced.search.api.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.basic.api.base.IBaseReadOnlyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 9/30/2017.
 */
public interface IBaseAdvancedSearchRepository<I extends Serializable, E extends IBasePersistenceModel<I>, S extends IBaseSearchObject>
        extends IBaseReadOnlyRepository<I, E> {

    Long countBySearchObject(S example);
    List<E> searchBySearchObject(S example);
    Page<E> searchBySearchObject(S example, Pageable pagingDto);
}
