package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.IVaselineSecurityActionApi;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IAuthorizationActionApi.class
)
public class VaselineAuthorizationActionApiImpl
        extends ProxyAwareImpl
        implements IAuthorizationActionApi {

    private ServiceTracker<IVaselineSecurityActionApi, IVaselineSecurityActionApi> actionApiTracker;

    @Activate
    public void initialize(ComponentContext componentContext) {
        actionApiTracker = new ServiceTracker<IVaselineSecurityActionApi, IVaselineSecurityActionApi>(
                componentContext.getBundleContext(),
                IVaselineSecurityActionApi.class,
                null
                );
        actionApiTracker.open();
    }

    @Deactivate
    public void finish() {
        actionApiTracker.close();
    }

    @Override
    public List<String> getActionChildTreeNames(final String baseActionTN) {
        return actionApiTracker.getService().getActionChildTreeNames(baseActionTN);
    }

    @Override
    public <R> R doBusinessAction(final IBusinessAction<R> businessAction) throws BaseVaselineServerException {
        throw new BaseVaselineServerException("Not supported");
    }

}
