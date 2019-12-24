package ir.amv.os.vaseline.data.jpa.search.advanced.spring;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;
import ir.amv.os.vaseline.data.jpa.search.advanced.api.server.dao.IDefaultJpaAdvancedSearchDao;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseAdvancedSearchRepository<I extends Serializable, E extends IBasePersistenceModel<I>, S extends IBaseSearchObject>
        extends SimpleJpaRepository<E, I>
        implements IBaseAdvancedSearchSpringRepository<I, E, S>, IDefaultJpaAdvancedSearchDao<I, E, S> {

    private final EntityManager em;

    public BaseAdvancedSearchRepository(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public Class<E> getPersistentModelClass() {
        return GenericUtils.getGeneric(getClass(), IBaseAdvancedSearchRepository.class, 1);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
