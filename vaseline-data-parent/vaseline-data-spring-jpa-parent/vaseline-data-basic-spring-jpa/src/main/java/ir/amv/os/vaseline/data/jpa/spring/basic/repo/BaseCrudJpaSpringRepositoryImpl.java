package ir.amv.os.vaseline.data.jpa.spring.basic.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;

public class BaseCrudJpaSpringRepositoryImpl<I extends Serializable, E extends IBasePersistenceModel<I>>
        extends SimpleJpaRepository<E, I>
        implements VaselineCrudJpaRepository<I, E> {

    private final EntityManager em;

    public BaseCrudJpaSpringRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * {@inheritDoc}
     * @implNote change visibility
     */
    @Override
    public <F extends E> Page<F> readPage(TypedQuery<F> query, Class<F> domainClass, Pageable pageable, Specification<F> spec) {
        return super.readPage(query, domainClass, pageable, spec);
    }
}
