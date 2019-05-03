package ir.amv.os.vaseline.data.osgi.feature.basic.dao;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud.IDefaultJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;

import javax.persistence.EntityManager;

public class SampleBasicDao
        implements
        IDefaultJpaCrudDao<SampleEntity, Long>, IBaseCrudDao<SampleEntity, Long> {

    private final EntityManager entityManager;

    public SampleBasicDao(EntityManager entityManager) {
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
