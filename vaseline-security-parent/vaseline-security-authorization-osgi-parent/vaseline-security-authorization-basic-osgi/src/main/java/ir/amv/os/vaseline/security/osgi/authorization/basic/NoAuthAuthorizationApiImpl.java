package ir.amv.os.vaseline.security.osgi.authorization.basic;

import ir.amv.os.vaseline.security.apis.authorization.basicimpl.server.IImplementedNoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.INoAuthAuthorizationApi;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Timer;
import java.util.TimerTask;

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
