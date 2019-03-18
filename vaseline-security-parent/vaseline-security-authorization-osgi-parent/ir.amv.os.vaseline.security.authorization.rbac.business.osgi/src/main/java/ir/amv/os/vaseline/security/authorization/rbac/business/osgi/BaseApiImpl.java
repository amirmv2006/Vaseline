package ir.amv.os.vaseline.security.authorization.rbac.business.osgi;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.basic.def.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.business.def.IBaseImplementedNotSecuredCrudApi;
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
