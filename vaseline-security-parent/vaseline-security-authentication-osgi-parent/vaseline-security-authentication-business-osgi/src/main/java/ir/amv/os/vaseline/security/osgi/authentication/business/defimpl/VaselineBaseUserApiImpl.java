package ir.amv.os.vaseline.security.osgi.authentication.business.defimpl;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.apis.audit.basic.server.IAuditApi;
import ir.amv.os.vaseline.security.apis.authentication.business.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.businessimpl.server.base.IImplementedBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineBaseUserEntity;
import ir.amv.os.vaseline.security.osgi.authentication.business.IVaselineBaseUserApi;
import ir.amv.os.vaseline.security.osgi.authentication.dao.jpa.IVaselineBaseUserDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineBaseUserApi.class,
                IBaseUserApi.class
        }
)
public class VaselineBaseUserApiImpl
        implements IVaselineBaseUserApi, IImplementedBaseUserApi<VaselineBaseUserEntity,
        IVaselineBaseUserDao>{
    private IAuditApi auditApi;
    private IVaselineBaseUserDao dao;
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public IAuditApi getAuditApi() {
        return auditApi;
    }

    @Override
    public IVaselineBaseUserDao getDao() {
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

    @Reference
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }

    @Reference
    public void setAuditApi(final IAuditApi auditApi) {
        this.auditApi = auditApi;
    }

    @Reference
    public void setDao(final IVaselineBaseUserDao dao) {
        this.dao = dao;
    }
}
