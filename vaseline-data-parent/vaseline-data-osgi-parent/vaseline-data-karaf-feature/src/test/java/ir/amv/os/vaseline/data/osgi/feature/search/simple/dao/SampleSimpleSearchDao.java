package ir.amv.os.vaseline.data.osgi.feature.search.simple.dao;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.jpa.search.simple.api.server.dao.IDefaultJpaSimpleSearchDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;

import javax.persistence.EntityManager;

public class SampleSimpleSearchDao
        implements
        IDefaultJpaSimpleSearchDao<Long, SampleEntity, SampleDto>,
        ISampleSimpleSearchDao {

    private final EntityManager entityManager;

    public SampleSimpleSearchDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return null;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
