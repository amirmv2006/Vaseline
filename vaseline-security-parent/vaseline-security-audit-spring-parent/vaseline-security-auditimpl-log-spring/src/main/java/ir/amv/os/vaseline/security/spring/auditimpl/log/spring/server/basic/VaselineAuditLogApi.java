package ir.amv.os.vaseline.security.spring.auditimpl.log.spring.server.basic;

import ir.amv.os.vaseline.basics.core.api.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.audit.def.log.server.basic.IImplementedAuditLogApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Amir
 */
@Component
public class VaselineAuditLogApi
        extends ProxyAwareImpl
        implements IImplementedAuditLogApi {

    private IVaselineLogger vaselineLogger;
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public IVaselineLogger getVaselineLogger() {
        return vaselineLogger;
    }

    @Autowired
    public void setVaselineLogger(final IVaselineLogger vaselineLogger) {
        this.vaselineLogger = vaselineLogger;
    }

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Autowired
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }
}
