package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.crud.IBaseImplementedJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryDaoImpl
        implements IBaseImplementedJpaCrudDao<TestCountryEntity, Long>, ITestCountryDao {

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
    public void setEntityClass(final Class<TestCountryEntity> entityClass) {
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return vendorSpecificDaoHelper;
    }

    @Override
    public Class<? extends TestCountryEntity> getEntityClass() {
        return TestCountryEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
