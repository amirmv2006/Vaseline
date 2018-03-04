package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.role.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role.SecurityRoleEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.BaseApiImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.role.IVaselineSecurityRoleApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.role.IVaselineSecurityRoleDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineSecurityRoleApi.class,
                IProxyAware.class
        }
)
public class VaselineSecurityRoleApiImpl
        extends BaseApiImpl<SecurityRoleEntity, IVaselineSecurityRoleDao>
        implements IVaselineSecurityRoleApi {

    private IVaselineSecurityRoleDao dao;

    @Override
    public IVaselineSecurityRoleDao getDao() {
        return dao;
    }

    @Reference
    public void setDao(final IVaselineSecurityRoleDao dao) {
        this.dao = dao;
    }
}
