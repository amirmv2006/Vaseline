package ir.amv.os.vaseline.data.osgi.feature.search.advanced.dao;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.jpa.search.advanced.api.server.dao.IDefaultJpaAdvancedSearchDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;

import javax.persistence.EntityManager;

public class SampleAdvancedSearchDao
        implements IDefaultJpaAdvancedSearchDao<SampleEntity, SampleSearchObject, Long>,
        ISampleAdvancedSearchDao {
    private EntityManager em;

    public SampleAdvancedSearchDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return null;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
