package ir.amv.os.vaseline.data.jpa.spring.advanced.search.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.jpa.spring.basic.repo.VaselineReadOnlyJpaRepository;

import java.io.Serializable;

public interface VaselineAdvancedSearchJpaRepository<I extends Serializable, E extends IBasePersistenceModel<I>, S extends IBaseSearchObject>
        extends VaselineReadOnlyJpaRepository<I, E>, IDefaultAdvancedSearchJpaRepository<I, E, S> {
}
