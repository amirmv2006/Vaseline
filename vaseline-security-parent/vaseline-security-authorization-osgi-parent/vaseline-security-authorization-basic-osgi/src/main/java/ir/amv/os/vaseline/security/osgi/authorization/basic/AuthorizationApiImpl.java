package ir.amv.os.vaseline.security.osgi.authorization.basic;

import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authorization.basicimpl.server.IImplementedAuthorizationApi;
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
