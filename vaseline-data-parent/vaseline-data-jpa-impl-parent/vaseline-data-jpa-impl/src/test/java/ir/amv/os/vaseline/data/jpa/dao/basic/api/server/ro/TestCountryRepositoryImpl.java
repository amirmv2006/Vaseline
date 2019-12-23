package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryRepositoryImpl
        implements IDefaultJpaCrudRepository<Long, TestCountryBusinessModel>, ITestCountryRepository {

    private EntityManager em;

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Class<? extends TestCountryBusinessModel> getPersistentModelClass() {
        return TestCountryBusinessModel.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
