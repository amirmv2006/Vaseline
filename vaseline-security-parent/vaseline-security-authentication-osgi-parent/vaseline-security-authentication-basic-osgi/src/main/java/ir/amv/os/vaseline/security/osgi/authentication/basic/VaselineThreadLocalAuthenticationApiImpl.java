package ir.amv.os.vaseline.security.osgi.authentication.basic;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.IImplementedThreadLocalAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.ISetAuthenticationApi;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IAuthenticationApi.class,
                ISetAuthenticationApi.class
        }
)
public class VaselineThreadLocalAuthenticationApiImpl
        extends ProxyAwareImpl
        implements IImplementedThreadLocalAuthenticationApi, IAuthenticationApi {

    private IVaselineBusinessActionExecutor businessActionExecutor;
    private ThreadLocal<String> currentUsernameThreadLocal = new InheritableThreadLocal<>();

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Reference
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }

    @Override
    public ThreadLocal<String> getCurrentUsernameThreadLocal() {
        return currentUsernameThreadLocal;
    }

}
