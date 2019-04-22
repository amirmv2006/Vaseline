package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.user.defimpl;

import ir.amv.os.vaseline.basics.core.api.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.security.audit.basic.api.server.IAuditApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.def.user.IDefaultSecurityUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.BaseApiImpl;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.user.IVaselineSecurityUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.user.SecurityUserEntity;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.user.IVaselineSecurityUserDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineSecurityUserApi.class,
                IProxyAware.class
        }
)
public class VaselineSecurityUserApiImpl
        extends BaseApiImpl<SecurityUserEntity, IVaselineSecurityUserDao>
        implements IDefaultSecurityUserApi<SecurityUserEntity, IVaselineSecurityUserDao>,
        IVaselineSecurityUserApi {
    private IAuditApi auditApi;
    private IVaselineSecurityUserDao dao;

    @Override
    public IAuditApi getAuditApi() {
        return auditApi;
    }

    @Reference
    public void setAuditApi(final IAuditApi auditApi) {
        this.auditApi = auditApi;
    }

    @Override
    public IVaselineSecurityUserDao getDao() {
        return dao;
    }

    @Reference
    public void setDao(final IVaselineSecurityUserDao dao) {
        this.dao = dao;
    }
}
