package ir.amv.os.vaseline.security.spring.auditimpl.log.spring.server.basic;

import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.security.apis.auditimpl.log.server.basic.IImplementedAuditLogApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Amir
 */
@Component
public class VaselineAuditLogApi
        implements IImplementedAuditLogApi {

    private IVaselineLogger vaselineLogger;

    @Override
    public IVaselineLogger getVaselineLogger() {
        return vaselineLogger;
    }

    @Autowired
    public void setVaselineLogger(final IVaselineLogger vaselineLogger) {
        this.vaselineLogger = vaselineLogger;
    }

    @Override
    public <Proxy> Proxy getProxy(final Class<Proxy> proxyClass) {
        return null;
    }

    @Override
    public <Proxy> void setProxy(final Proxy proxy) {

    }
}