package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.user.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.security.apis.audit.basic.server.IAuditApi;
import ir.amv.os.vaseline.security.apis.authentication.businessimpl.server.base.IImplementedBaseUserApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.user.IImplementedSecurityUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.user.SecurityUserEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.BaseApiImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.user.IVaselineSecurityUserApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.user.IVaselineSecurityUserDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineSecurityUserApi.class
        }
)
public class VaselineSecurityUserApiImpl
        extends BaseApiImpl<SecurityUserEntity, IVaselineSecurityUserDao>
        implements IImplementedSecurityUserApi<SecurityUserEntity, IVaselineSecurityUserDao>,
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
