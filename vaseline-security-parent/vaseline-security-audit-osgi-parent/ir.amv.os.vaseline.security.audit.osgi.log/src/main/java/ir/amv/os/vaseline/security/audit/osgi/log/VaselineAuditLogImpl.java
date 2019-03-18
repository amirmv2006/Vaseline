package ir.amv.os.vaseline.security.audit.osgi.log;

import ir.amv.os.vaseline.basics.core.api.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.basics.core.api.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.audit.basic.api.server.IAuditApi;
import ir.amv.os.vaseline.security.audit.def.log.server.basic.IImplementedAuditLogApi;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IAuditApi.class,
                IProxyAware.class
        }
)
public class VaselineAuditLogImpl
        extends ProxyAwareImpl
        implements IAuditApi, IImplementedAuditLogApi{

    private IVaselineLogger vaselineLogger;
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public IVaselineLogger getVaselineLogger() {
        return vaselineLogger;
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
    public void setVaselineLogger(final IVaselineLogger vaselineLogger) {
        this.vaselineLogger = vaselineLogger;
    }
}
