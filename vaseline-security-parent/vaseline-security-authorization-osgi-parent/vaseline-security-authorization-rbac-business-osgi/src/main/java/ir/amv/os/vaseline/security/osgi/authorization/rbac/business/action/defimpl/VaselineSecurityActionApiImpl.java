package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.action.IImplementedSecurityActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.BaseApiImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.IVaselineSecurityActionApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.IVaselineSecurityActionDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineSecurityActionApi.class,
                IProxyAware.class
        }
)
public class VaselineSecurityActionApiImpl
        extends BaseApiImpl<SecurityActionEntity, IVaselineSecurityActionDao>
        implements IImplementedSecurityActionApi<SecurityActionEntity, IVaselineSecurityActionDao>,
        IVaselineSecurityActionApi{

    private IVaselineSecurityActionDao dao;

    @Override
    public IVaselineSecurityActionDao getDao() {
        return dao;
    }

    @Reference
    public void setDao(final IVaselineSecurityActionDao dao) {
        this.dao = dao;
    }
}
