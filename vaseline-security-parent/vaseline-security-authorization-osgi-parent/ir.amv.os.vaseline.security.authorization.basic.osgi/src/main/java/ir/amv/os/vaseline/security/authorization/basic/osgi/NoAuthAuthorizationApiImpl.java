package ir.amv.os.vaseline.security.authorization.basic.osgi;

import ir.amv.os.vaseline.security.authorization.basic.def.server.IDefaultNoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.INoAuthAuthorizationApi;
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
        implements IDefaultNoAuthAuthorizationApi,
        INoAuthAuthorizationApi {
    // avoiding cycle -> these api use crud apis which depend on authorization api
    private IAuthorizationUserApi authorizationUserApiTracker;
    private IAuthorizationActionApi authorizationActionApiTracker;

    @Override
    public IAuthorizationUserApi getAuthorizationUserApi() {
        return authorizationUserApiTracker;
    }

    @Override
    public IAuthorizationActionApi getAuthorizationActionApi() {
        return authorizationActionApiTracker;
    }

    @Reference
    public void setAuthorizationActionApiTracker(final IAuthorizationActionApi authorizationActionApiTracker) {
        this.authorizationActionApiTracker = authorizationActionApiTracker;
    }

    @Reference
    public void setAuthorizationUserApiTracker(final IAuthorizationUserApi authorizationUserApiTracker) {
        this.authorizationUserApiTracker = authorizationUserApiTracker;
    }
}
