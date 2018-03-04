package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.defimpl;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.action.IImplementedSecurityActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.IVaselineSecurityActionApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.IVaselineSecurityActionDao;

/**
 * @author Amir
 */
public class VaselineSecurityActionApiImpl
        implements IImplementedSecurityActionApi<SecurityActionEntity, IVaselineSecurityActionDao>,
        IVaselineSecurityActionApi{
    @Override
    public IVaselineSecurityActionDao getDao() {
        return null;
    }

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return null;
    }

    @Override
    public <Proxy> Proxy getProxy(final Class<Proxy> proxyClass) {
        return null;
    }

    @Override
    public <Proxy> void setProxy(final Proxy proxy) {

    }
}
