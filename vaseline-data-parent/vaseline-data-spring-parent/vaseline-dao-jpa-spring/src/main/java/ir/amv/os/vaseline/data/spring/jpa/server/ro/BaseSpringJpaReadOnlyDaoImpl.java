package ir.amv.os.vaseline.data.spring.jpa.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IBaseImplementedJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.spring.jpa.server.base.BaseSpringJpaDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringJpaReadOnlyDaoImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseSpringJpaDaoImpl
        implements IBaseImplementedJpaReadOnlyDao<E, Id> {
    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return new SpringJpaVendorSpecificDaoHelperImpl();
    }

}
