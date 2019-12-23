package ir.amv.os.vaseline.data.spring.jpa.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaPersistentModelRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.spring.jpa.server.base.BaseSpringJpaDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringJpaPersistentModelDaoImpl<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends BaseSpringJpaDaoImpl
        implements IDefaultJpaPersistentModelRepository<I, E> {
    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return new SpringJpaVendorSpecificDaoHelperImpl();
    }

}
