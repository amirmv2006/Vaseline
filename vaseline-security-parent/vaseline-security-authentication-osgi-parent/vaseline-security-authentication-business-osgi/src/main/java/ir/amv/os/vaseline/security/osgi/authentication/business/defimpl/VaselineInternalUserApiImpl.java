package ir.amv.os.vaseline.security.osgi.authentication.business.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.security.apis.audit.basic.server.IAuditApi;
import ir.amv.os.vaseline.security.apis.authentication.business.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.businessimpl.server.base.IImplementedBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineInternalUserEntity;
import ir.amv.os.vaseline.security.osgi.authentication.business.IVaselineInternalUserApi;
import ir.amv.os.vaseline.security.osgi.authentication.dao.jpa.IVaselineInternalUserDao;
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
        IImplementedBaseUserApi<VaselineInternalUserEntity,IVaselineInternalUserDao>,
        IBaseImplementedCrudApi<VaselineInternalUserEntity, Long, IVaselineInternalUserDao> {

    private IAuditApi auditApi;
    private IVaselineInternalUserDao dao;
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public IAuditApi getAuditApi() {
        return auditApi;
    }

    @Override
    public IVaselineInternalUserDao getDao() {
        return dao;
    }

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Reference
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
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
