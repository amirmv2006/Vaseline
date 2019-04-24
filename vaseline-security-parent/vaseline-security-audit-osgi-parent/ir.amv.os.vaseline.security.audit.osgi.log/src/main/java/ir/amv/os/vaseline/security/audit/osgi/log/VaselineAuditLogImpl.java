package ir.amv.os.vaseline.security.audit.osgi.log;

import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyAware;
import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.security.audit.basic.api.server.IAuditApi;
import ir.amv.os.vaseline.security.audit.def.log.server.basic.IDefaultAuditLogApi;
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
        implements IAuditApi, IDefaultAuditLogApi {

    private IVaselineLogger vaselineLogger;

    @Override
    public IVaselineLogger getVaselineLogger() {
        return vaselineLogger;
    }

    @Reference
    public void setVaselineLogger(final IVaselineLogger vaselineLogger) {
        this.vaselineLogger = vaselineLogger;
    }
}
