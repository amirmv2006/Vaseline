package ir.amv.os.vaseline.data.jpa.search.advanced.api.server.dao;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryDaoImpl
        implements ITestCountryDao,
        IDefaultJpaAdvancedSearchDao<Long, TestCountryBusinessModel, ITestCountrySearchObject> {

    private EntityManager em;

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Class<TestCountryBusinessModel> getPersistentModelClass() {
        return TestCountryBusinessModel.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
