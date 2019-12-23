package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCountryDaoImpl
        implements IDefaultHibernateReadOnlyDao<Long, TestCountryBusinessModel>, ITestCountryDao {

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
