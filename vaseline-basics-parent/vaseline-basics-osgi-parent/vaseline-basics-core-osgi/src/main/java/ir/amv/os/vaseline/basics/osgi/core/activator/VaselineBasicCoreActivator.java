package ir.amv.os.vaseline.basics.osgi.core.activator;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.basics.osgi.base.AbstractBundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

/**
 * @author Amir
 */
public class VaselineBasicCoreActivator extends AbstractBundleActivator {

    @Override
    protected void doStart(final BundleContext context) throws InvalidSyntaxException {
        ServiceListener serviceListener = event -> {
            ServiceReference<? extends IProxyAware> serviceReference = (ServiceReference<? extends IProxyAware>) event.getServiceReference();
            IProxyAware service = context.getService(serviceReference);
            Object unwrapped = unwrapProxy(service);
            service.setProxy(unwrapped);
        };
        addServiceListener(serviceListener, "(vaseline.layer=api)");
    }

    @Override
    protected void doStop(final BundleContext context) throws Exception {
    }

    private Object unwrapProxy(final IProxyAware service) {
        // TODO unwrap the main bean
        return service;
    }
}
