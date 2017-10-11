package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class TestCountryDaoImpl
        extends BaseReadOnlyHibernateDaoImpl<TestCountryEntity, Long>
        implements ITestCountryDao {

    @Override
    @Inject
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
