package ir.amv.os.vaseline.data.jpa.search.advanced.api.server.dao;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud.IDefaultJpaCrudRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryDaoImpl
        implements ITestCountryDao,
        IDefaultJpaCrudRepository<Long, TestCountryBusinessModel>,
        IDefaultJpaAdvancedSearchDao<Long, TestCountryBusinessModel, ITestCountrySearchObject> {

    private EntityManager em;
    private IVendorSpecificDaoHelper vendorSpecificDaoHelper;

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Inject
    public void setVendorSpecificDaoHelper(final IVendorSpecificDaoHelper vendorSpecificDaoHelper) {
        this.vendorSpecificDaoHelper = vendorSpecificDaoHelper;
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return vendorSpecificDaoHelper;
    }

    @Override
    public void setEntityClass(final Class<TestCountryBusinessModel> entityClass) {
    }

    @Override
    public Class<? extends TestCountryBusinessModel> getEntityClass() {
        return TestCountryBusinessModel.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
