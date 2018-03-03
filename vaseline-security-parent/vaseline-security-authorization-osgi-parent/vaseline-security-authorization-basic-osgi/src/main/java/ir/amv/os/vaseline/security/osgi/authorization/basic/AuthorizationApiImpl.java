package ir.amv.os.vaseline.security.osgi.authorization.basic;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.api.IImplementedAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.INoAuthAuthorizationApi;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IAuthorizationApi.class
)
public class AuthorizationApiImpl
        implements IImplementedAuthorizationApi,
        IAuthorizationApi {
    private IAuthenticationApi authenticationApi;
    private INoAuthAuthorizationApi noAuthAuthorizationApi;
    private IVaselineBusinessActionExecutor businessActionExecutor;
    private Object proxy;// TODO move these to a super class

    @Override
    public IAuthenticationApi getAuthenticationApi() {
        return authenticationApi;
    }

    @Override
    public INoAuthAuthorizationApi getNoAuthAuthorizationApi() {
        return noAuthAuthorizationApi;
    }

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Override
    public <Proxy> Proxy getProxy(final Class<Proxy> proxyClass) {
        return (Proxy) proxy;
    }

    @Override
    public <Proxy> void setProxy(final Proxy proxy) {
        this.proxy = proxy;
    }

    @Reference
    public void setAuthenticationApi(final IAuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }

    @Reference
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }

    @Reference
    public void setNoAuthAuthorizationApi(final INoAuthAuthorizationApi noAuthAuthorizationApi) {
        this.noAuthAuthorizationApi = noAuthAuthorizationApi;
    }
}
