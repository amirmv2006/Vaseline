package ir.amv.os.vaseline.data.basic.api.base;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyRepository<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends IBaseRepository {

    Class<E> getPersistentModelClass();

    Optional<E> findById(I id);

    long count();

    List<E> findAll();

    Page<E> findAll(Pageable page);
}
