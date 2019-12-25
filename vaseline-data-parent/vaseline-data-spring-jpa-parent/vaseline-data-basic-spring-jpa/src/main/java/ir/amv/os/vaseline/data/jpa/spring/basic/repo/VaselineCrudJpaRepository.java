package ir.amv.os.vaseline.data.jpa.spring.basic.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.basic.api.base.IBaseCrudRepository;

import java.io.Serializable;

public interface VaselineCrudJpaRepository<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends IBaseCrudRepository<I, E>,
        VaselineReadOnlyJpaRepository<I, E> {

}
