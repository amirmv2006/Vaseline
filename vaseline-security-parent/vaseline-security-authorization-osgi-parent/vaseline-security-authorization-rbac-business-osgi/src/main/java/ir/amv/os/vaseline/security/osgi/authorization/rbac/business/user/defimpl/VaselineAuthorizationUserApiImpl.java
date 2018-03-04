package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.user.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.IVaselineSecurityActionApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.user.IVaselineSecurityUserApi;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * to fix the loop
 * @author Amir
 */
@Component(
        immediate = true,
        service = IAuthorizationUserApi.class
)
public class VaselineAuthorizationUserApiImpl
        extends ProxyAwareImpl
        implements IAuthorizationUserApi {

    private ServiceTracker<IVaselineSecurityUserApi, IVaselineSecurityUserApi> userApiTracker;

    @Activate
    public void initialize(ComponentContext componentContext) {
        userApiTracker = new ServiceTracker<IVaselineSecurityUserApi, IVaselineSecurityUserApi>(
                componentContext.getBundleContext(),
                IVaselineSecurityUserApi.class,
                null
        );
        userApiTracker.open();
    }

    @Deactivate
    public void finish() {
        userApiTracker.close();
    }

    @Override
    public boolean hasUserAccessToAction(final String username, final String actionTreeName) throws BaseVaselineServerException {
        return userApiTracker.getService().hasUserAccessToAction(username, actionTreeName);
    }

    @Override
    public List<String> getUserActionTreeNames(final String currentUsername) throws BaseVaselineServerException {
        return userApiTracker.getService().getUserActionTreeNames(currentUsername);
    }

    @Override
    public List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName) throws BaseVaselineServerException {
        return userApiTracker.getService().getUsernamesWithAccessToActionTreeName(actionTreeName);
    }

    @Override
    public <R> R doBusinessAction(final IBusinessAction<R> businessAction) throws BaseVaselineServerException {
        throw new BaseVaselineServerException("Not supported");
    }

}
