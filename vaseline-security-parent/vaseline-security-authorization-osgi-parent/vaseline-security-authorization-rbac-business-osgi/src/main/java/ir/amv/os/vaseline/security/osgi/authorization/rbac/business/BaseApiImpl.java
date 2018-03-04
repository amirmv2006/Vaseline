package ir.amv.os.vaseline.security.osgi.authorization.rbac.business;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.role.IImplementedSecurityRoleApi;

/**
 * @author Amir
 */
public class BaseApiImpl<E extends IBaseEntity<Long>, Dao extends IBaseReadOnlyDao<E, Long>>
        extends IProxyAware
        implements IBaseImplementedReadOnlyApi<E, Long, Dao> {
    private Dao dao;
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public Dao getDao() {
        return dao;
    }

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Override
    public <Proxy> Proxy getProxy(final Class<Proxy> proxyClass) {
        return null;
    }

    @Override
    public <Proxy> void setProxy(final Proxy proxy) {

    }
}
