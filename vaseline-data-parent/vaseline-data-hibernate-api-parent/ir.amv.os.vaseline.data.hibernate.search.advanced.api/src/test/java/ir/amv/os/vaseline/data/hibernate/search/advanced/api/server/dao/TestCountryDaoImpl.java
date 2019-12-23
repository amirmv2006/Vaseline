package ir.amv.os.vaseline.data.hibernate.search.advanced.api.server.dao;

import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.crud.IDefaultHibernateCrudDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class TestCountryDaoImpl
        implements ITestCountryDao,
        IDefaultHibernateCrudDao<Long, TestCountryBusinessModel>,
        IDefaultHibernateAdvancedSearchDao<Long, TestCountryBusinessModel, ITestCountrySearchObject> {

    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Inject
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
