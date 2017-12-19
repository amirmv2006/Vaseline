package ir.amv.os.vaseline.basics.osgi.core.activator;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

/**
 * @author Amir
 */
public class VaselineBasicCoreActivator implements BundleActivator {

    private ServiceListener serviceListener;

    @Override
    public void start(final BundleContext context) throws Exception {
        serviceListener = event -> {
            ServiceReference<? extends IProxyAware> serviceReference = (ServiceReference<? extends IProxyAware>) event.getServiceReference();
            IProxyAware service = context.getService(serviceReference);
            Object unwrapped = unwrapProxy(service);
            service.setProxy(unwrapped);
        };
        context.addServiceListener(serviceListener, "(vaseline.layer=api)");
    }

    private Object unwrapProxy(final IProxyAware service) {
        // TODO unwrap the main bean
        return service;
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        context.removeServiceListener(serviceListener);
        serviceListener = null;
    }
}
