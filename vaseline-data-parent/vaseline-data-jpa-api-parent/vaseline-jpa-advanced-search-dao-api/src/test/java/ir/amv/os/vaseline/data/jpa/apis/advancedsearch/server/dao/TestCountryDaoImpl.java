package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.crud.IBaseImplementedJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryDaoImpl
        implements ITestCountryDao,
        IBaseImplementedJpaCrudDao<TestCountryEntity, Long>,
        IBaseImplementedJpaAdvancedSearchDao<TestCountryEntity, ITestCountrySearchObject, Long>{

    private EntityManager em;

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return null;
    }

    @Override
    public void setEntityClass(final Class<TestCountryEntity> entityClass) {
    }

    @Override
    public Class<TestCountryEntity> getEntityClass() {
        return TestCountryEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
