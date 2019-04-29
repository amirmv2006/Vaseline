package ir.amv.os.vaseline.security.spring.auditimpl.log.spring.server.basic;

import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.security.audit.def.log.server.basic.IDefaultAuditLogApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Amir
 */
@Component
public class VaselineAuditLogApi
        extends ProxyAwareImpl
        implements IDefaultAuditLogApi {

    private IVaselineLogger vaselineLogger;

    @Override
    public IVaselineLogger getVaselineLogger() {
        return vaselineLogger;
    }

    @Autowired
    public void setVaselineLogger(final IVaselineLogger vaselineLogger) {
        this.vaselineLogger = vaselineLogger;
    }

}
