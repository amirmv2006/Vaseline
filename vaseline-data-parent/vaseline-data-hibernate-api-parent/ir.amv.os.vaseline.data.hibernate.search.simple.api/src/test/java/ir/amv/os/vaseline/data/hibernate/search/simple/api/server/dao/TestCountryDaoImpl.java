package ir.amv.os.vaseline.data.hibernate.search.simple.api.server.dao;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCountryDaoImpl
        implements IBaseImplementedHibernateSimpleSearchDao<TestCountryEntity, TestCountryDto, Long>, ITestCountryDao {

    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Class<TestCountryDto> getDtoClass() {
        return null;
    }
}
