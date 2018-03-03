package ir.amv.os.vaseline.security.spring.auditimpl.log.spring.server.basic;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.PorxyAwareImpl;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.apis.auditimpl.log.server.basic.IImplementedAuditLogApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Amir
 */
@Component
public class VaselineAuditLogApi
        extends PorxyAwareImpl
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
