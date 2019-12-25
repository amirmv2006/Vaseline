package ir.amv.os.vaseline.data.jpa.spring.basic.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Just a helper class to reverse the generic order of spring repositories. MPS has problems with this order because
 * usually the model type will depend on the id type.
 *
 * @param <I> the id type
 * @param <E> the model type
 */
public interface VaselineReadOnlyJpaRepository<I extends Serializable, E extends IBasePersistenceModel<I>> extends
        IDefaultReadOnlyJpaRepository<I, E>,
        JpaRepository<E, I> {

}
