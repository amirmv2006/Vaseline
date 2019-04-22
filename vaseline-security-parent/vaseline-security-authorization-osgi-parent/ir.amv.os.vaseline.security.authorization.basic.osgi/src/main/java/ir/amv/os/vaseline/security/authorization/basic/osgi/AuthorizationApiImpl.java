package ir.amv.os.vaseline.security.authorization.basic.osgi;

import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.authorization.basic.def.server.IDefaultAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.INoAuthAuthorizationApi;
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
        implements IDefaultAuthorizationApi,
        IAuthorizationApi {
    private IAuthenticationApi authenticationApi;
    private INoAuthAuthorizationApi noAuthAuthorizationApi;

    @Override
    public IAuthenticationApi getAuthenticationApi() {
        return authenticationApi;
    }

    @Override
    public INoAuthAuthorizationApi getNoAuthAuthorizationApi() {
        return noAuthAuthorizationApi;
    }

    @Reference
    public void setAuthenticationApi(final IAuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }

    @Reference
    public void setNoAuthAuthorizationApi(final INoAuthAuthorizationApi noAuthAuthorizationApi) {
        this.noAuthAuthorizationApi = noAuthAuthorizationApi;
    }
}
