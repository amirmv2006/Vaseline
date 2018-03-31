package ir.amv.os.vaseline.data.spring.hibernate.server.base;

import ir.amv.os.vaseline.data.apis.dao.basic.server.base.defimpl.BaseDaoImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.base.IBaseHibernateDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Amir
 */
public class BaseSpringHibernateDaoImpl
        extends BaseDaoImpl
        implements IBaseHibernateDao {
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
