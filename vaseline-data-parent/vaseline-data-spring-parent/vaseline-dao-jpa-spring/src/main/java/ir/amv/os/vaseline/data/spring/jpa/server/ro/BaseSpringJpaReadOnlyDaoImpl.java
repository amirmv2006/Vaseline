package ir.amv.os.vaseline.data.spring.jpa.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.spring.jpa.server.base.BaseSpringJpaDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringJpaReadOnlyDaoImpl<I extends Serializable, E extends IBaseEntity<I>>
        extends BaseSpringJpaDaoImpl
        implements IDefaultJpaReadOnlyDao<I, E> {
    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return new SpringJpaVendorSpecificDaoHelperImpl();
    }

}
