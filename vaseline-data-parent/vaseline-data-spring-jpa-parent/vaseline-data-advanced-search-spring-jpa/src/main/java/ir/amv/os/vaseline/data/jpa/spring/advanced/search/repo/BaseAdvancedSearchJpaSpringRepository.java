package ir.amv.os.vaseline.data.jpa.spring.advanced.search.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.jpa.spring.basic.repo.BaseCrudJpaSpringRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseAdvancedSearchJpaSpringRepository<I extends Serializable, E extends IBasePersistenceModel<I>, S extends IBaseSearchObject>
        extends BaseCrudJpaSpringRepositoryImpl<I, E>
        implements VaselineAdvancedSearchJpaRepository<I, E, S>, IDefaultAdvancedSearchJpaRepository<I, E, S> {

    public BaseAdvancedSearchJpaSpringRepository(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
