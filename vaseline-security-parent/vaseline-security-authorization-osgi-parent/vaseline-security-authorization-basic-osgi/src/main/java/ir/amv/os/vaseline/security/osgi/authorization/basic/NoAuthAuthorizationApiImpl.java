package ir.amv.os.vaseline.security.osgi.authorization.basic;

import ir.amv.os.vaseline.security.apis.authorization.basicimpl.server.IImplementedNoAuthAuthorizationApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.INoAuthAuthorizationApi;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
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
    private ServiceTracker<IAuthorizationUserApi, IAuthorizationUserApi> authorizationUserApiTracker;
    private ServiceTracker<IAuthorizationActionApi, IAuthorizationActionApi> authorizationActionApiTracker;

    @Activate
    public void init(ComponentContext componentContext) {
        authorizationActionApiTracker = new ServiceTracker<IAuthorizationActionApi, IAuthorizationActionApi>(
                componentContext.getBundleContext(),
                IAuthorizationActionApi.class,
                null
        );
        authorizationUserApiTracker = new ServiceTracker<IAuthorizationUserApi, IAuthorizationUserApi>(
                componentContext.getBundleContext(),
                IAuthorizationUserApi.class,
                null
        );
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                authorizationActionApiTracker.open();
                authorizationUserApiTracker.open();
            }
        }, 1000);
    }

    @Deactivate
    public void destroy() {
        authorizationActionApiTracker.close();
        authorizationUserApiTracker.close();
    }

    @Override
    public IAuthorizationUserApi getAuthorizationUserApi() {
        return authorizationUserApiTracker.getService();
    }

    @Override
    public IAuthorizationActionApi getAuthorizationActionApi() {
        return authorizationActionApiTracker.getService();
    }

}
