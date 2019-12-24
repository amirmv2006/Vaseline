package ir.amv.os.vaseline.data.spring.jpa.server.base;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.base.IBaseJpaRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.base.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author Amir
 */
public class BaseSpringJpaRepositoryImpl
        extends BaseRepositoryImpl
        implements IBaseJpaRepository {

    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    public void setEntityManager(final EntityManager em) {
        this.em = em;
    }
}
