package ir.amv.os.vaseline.security.osgi.authentication.basic;

import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.listener.ICurrentUserListener;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.server.IImplementedThreadLocalAuthenticationApi;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.server.ISetAuthenticationApi;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.util.tracker.ServiceTracker;

import java.util.ArrayList;
import java.util.List;

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
        implements IImplementedThreadLocalAuthenticationApi, IAuthenticationApi {

    private ThreadLocal<String> currentUsernameThreadLocal = new InheritableThreadLocal<>();
    private List<ICurrentUserListener> currentUserListeners = new ArrayList<>();
    private ServiceTracker<ICurrentUserListener, ICurrentUserListener> currentUserListenerTracker;

    @Activate
    public void init(ComponentContext componentContext) {
        BundleContext bundleContext = componentContext.getBundleContext();
        currentUserListenerTracker = new ServiceTracker<ICurrentUserListener, ICurrentUserListener>(
                bundleContext,
                ICurrentUserListener.class,
                null
        ) {
            @Override
            public ICurrentUserListener addingService(final ServiceReference<ICurrentUserListener> reference) {
                ICurrentUserListener currentUserListener = super.addingService(reference);
                addListener(currentUserListener);
                return currentUserListener;
            }

            @Override
            public void removedService(final ServiceReference<ICurrentUserListener> reference, final ICurrentUserListener service) {
                removeListener(service);
                super.removedService(reference, service);
            }
        };
        currentUserListenerTracker.open();
    }

    @Deactivate
    public void destroy() {
        currentUserListenerTracker.close();
        currentUserListenerTracker = null;
    }

    @Override
    public ThreadLocal<String> getCurrentUsernameThreadLocal() {
        return currentUsernameThreadLocal;
    }

    @Override
    public List<ICurrentUserListener> getListeners() {
        return currentUserListeners;
    }

}
