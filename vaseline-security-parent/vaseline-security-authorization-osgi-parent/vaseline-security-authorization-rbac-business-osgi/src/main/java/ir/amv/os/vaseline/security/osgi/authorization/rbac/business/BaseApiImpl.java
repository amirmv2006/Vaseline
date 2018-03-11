package ir.amv.os.vaseline.security.osgi.authorization.rbac.business;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.business.layerimpl.IBaseImplementedNotSecuredCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.business.layerimpl.IBaseImplementedNotSecuredReadOnlyApi;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
public abstract class BaseApiImpl<E extends IBaseEntity<Long>, Dao extends IBaseCrudDao<E, Long>>
        extends ProxyAwareImpl
        implements
        IBaseImplementedCrudApi<E, Long, Dao>,
        IBaseImplementedNotSecuredCrudApi<E, Long, Dao> {
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Reference
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }

}
