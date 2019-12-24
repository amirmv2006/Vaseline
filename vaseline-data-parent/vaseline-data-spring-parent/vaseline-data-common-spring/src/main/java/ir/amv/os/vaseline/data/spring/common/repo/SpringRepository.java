package ir.amv.os.vaseline.data.spring.common.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.dao.basic.api.ro.IBasePersistentModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Just a helper class to reverse the generic order of spring repositories. MPS has problems with this order because
 * usually the model type will depend on the id type.
 *
 * @param <I> the id type
 * @param <E> the model type
 */
public interface SpringRepository<I extends Serializable, E extends IBasePersistenceModel<I>> extends
        IBasePersistentModelRepository<I, E>,
        JpaRepository<E, I> {
}
