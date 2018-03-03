package ir.amv.os.vaseline.security.osgi.authorization.basic;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.security.apis.authorization.basic.api.IImplementedNoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.INoAuthAuthorizationApi;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = INoAuthAuthorizationApi.class
)
public class NoAuthAuthorizationApiImpl
        implements IImplementedNoAuthAuthorizationApi,
        INoAuthAuthorizationApi {
    private IAuthorizationUserApi authorizationUserApi;
    private IAuthorizationActionApi authorizationActionApi;
    private IVaselineBusinessActionExecutor businessActionExecutor;
    private Object proxy;

    @Override
    public IAuthorizationUserApi getAuthorizationUserApi() {
        return authorizationUserApi;
    }

    @Override
    public IAuthorizationActionApi getAuthorizationActionApi() {
        return authorizationActionApi;
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
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }

    @Reference
    public void setAuthorizationActionApi(final IAuthorizationActionApi authorizationActionApi) {
        this.authorizationActionApi = authorizationActionApi;
    }

    @Reference
    public void setAuthorizationUserApi(final IAuthorizationUserApi authorizationUserApi) {
        this.authorizationUserApi = authorizationUserApi;
    }

}
