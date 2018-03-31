package ir.amv.os.vaseline.data.spring.jpa.server.base;

import ir.amv.os.vaseline.data.apis.dao.basic.server.base.defimpl.BaseDaoImpl;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.base.IBaseJpaDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author Amir
 */
public class BaseSpringJpaDaoImpl
        extends BaseDaoImpl
        implements IBaseJpaDao {

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
