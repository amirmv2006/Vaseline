package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud.IDefaultJpaCrudRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryRepositoryImpl
        implements IDefaultJpaCrudRepository<Long, TestCountryBusinessModel>, ITestCountryRepository {

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
    public void setEntityClass(final Class<TestCountryBusinessModel> entityClass) {
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return vendorSpecificDaoHelper;
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
