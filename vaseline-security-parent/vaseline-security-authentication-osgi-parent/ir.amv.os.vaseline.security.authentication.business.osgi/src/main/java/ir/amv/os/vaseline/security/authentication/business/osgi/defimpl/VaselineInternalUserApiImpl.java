package ir.amv.os.vaseline.security.authentication.business.osgi.defimpl;

import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyAware;
import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.security.audit.basic.api.server.IAuditApi;
import ir.amv.os.vaseline.security.authentication.business.api.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.authentication.business.def.server.base.IDefaultBaseUserApi;
import ir.amv.os.vaseline.security.authentication.model.def.server.base.VaselineInternalUserEntity;
import ir.amv.os.vaseline.security.authentication.business.osgi.IVaselineInternalUserApi;
import ir.amv.os.vaseline.security.authentication.dao.jpa.osgi.IVaselineInternalUserDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineInternalUserApi.class,
                IBaseUserApi.class,
                IProxyAware.class
        }
)
public class VaselineInternalUserApiImpl
        extends ProxyAwareImpl
        implements IVaselineInternalUserApi,
        IDefaultBaseUserApi<VaselineInternalUserEntity,IVaselineInternalUserDao>,
        IDefaultCrudApi<VaselineInternalUserEntity, Long, IVaselineInternalUserDao> {

    private IAuditApi auditApi;
    private IVaselineInternalUserDao dao;

    @Override
    public IAuditApi getAuditApi() {
        return auditApi;
    }

    @Override
    public IVaselineInternalUserDao getDao() {
        return dao;
    }

    @Reference
    public void setAuditApi(final IAuditApi auditApi) {
        this.auditApi = auditApi;
    }

    @Reference
    public void setDao(final IVaselineInternalUserDao dao) {
        this.dao = dao;
    }
}
