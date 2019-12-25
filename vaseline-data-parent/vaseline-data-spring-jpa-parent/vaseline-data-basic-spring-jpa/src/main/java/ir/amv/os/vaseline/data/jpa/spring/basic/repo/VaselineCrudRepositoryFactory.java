package ir.amv.os.vaseline.data.jpa.spring.basic.repo;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class VaselineCrudRepositoryFactory<
            I extends Serializable,
            E extends IBasePersistenceModel<I>,
            R extends VaselineCrudJpaRepository<I, E>
        >
        extends JpaRepositoryFactoryBean<R, E, I> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public VaselineCrudRepositoryFactory(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new AdvancedSearchFactory(entityManager);
    }

    private static class AdvancedSearchFactory extends JpaRepositoryFactory {

        public AdvancedSearchFactory(EntityManager entityManager) {
            super(entityManager);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            //to check for QueryDslJpaRepository's which is out of scope.
            return BaseCrudJpaSpringRepositoryImpl.class;
        }
    }
}
