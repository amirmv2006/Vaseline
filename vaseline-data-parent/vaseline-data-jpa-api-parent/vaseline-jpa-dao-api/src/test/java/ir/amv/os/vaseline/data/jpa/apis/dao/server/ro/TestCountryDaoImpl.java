package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryDaoImpl
        extends BaseReadOnlyJpaDaoImpl<TestCountryEntity, Long>
        implements ITestCountryDao {

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
