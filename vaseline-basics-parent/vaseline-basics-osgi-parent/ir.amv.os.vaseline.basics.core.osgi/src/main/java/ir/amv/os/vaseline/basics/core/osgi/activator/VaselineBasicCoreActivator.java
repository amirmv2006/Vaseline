package ir.amv.os.vaseline.basics.core.osgi.activator;

import ir.amv.os.vaseline.basics.base.osgi.AbstractBundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Amir
 */
public class VaselineBasicCoreActivator extends AbstractBundleActivator {

    @Override
    protected void doStart(final BundleContext context) throws InvalidSyntaxException {
//        ServiceListener serviceListener = event -> {
//            ServiceReference<? extends IProxyAware> serviceReference = (ServiceReference<? extends IProxyAware>) event.getServiceReference();
//            IProxyAware service = context.getService(serviceReference);
//            Object unwrapped = unwrapProxy(service);
//            service.setProxy(unwrapped);
//        };
//        addServiceListener(serviceListener, "(vaseline.layer=api)");
    }

    @Override
    protected void doStop(final BundleContext context) throws Exception {
    }

//    private Object unwrapProxy(final IProxyAware service) {
        // TODO unwrap the main bean
//        return service;
//    }
}
