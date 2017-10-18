package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro;

import ir.amv.os.vaseline.basics.apis.jdbc.dialect.IVaselineJdbcDialect;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

public class TestCountryDaoImpl
        implements IBaseImplementedHibernateReadOnlyDao<TestCountryEntity, Long>, ITestCountryDao {

    private SessionFactory sessionFactory;

    @Override
    public void setEntityClass(final Class<TestCountryEntity> entityClass) {
    }

    @Override
    public Class<TestCountryEntity> getEntityClass() {
        return TestCountryEntity.class;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
