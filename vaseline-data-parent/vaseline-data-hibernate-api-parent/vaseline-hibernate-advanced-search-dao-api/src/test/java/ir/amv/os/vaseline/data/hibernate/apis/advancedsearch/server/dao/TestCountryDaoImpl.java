package ir.amv.os.vaseline.data.hibernate.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.data.hibernate.apis.dao.server.crud.IBaseImplementedHibernateCrudDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class TestCountryDaoImpl
        implements ITestCountryDao,
        IBaseImplementedHibernateCrudDao<TestCountryEntity, Long>,
        IBaseImplementedHibernateAdvancedSearchDao<TestCountryEntity, ITestCountrySearchObject, Long>{

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
