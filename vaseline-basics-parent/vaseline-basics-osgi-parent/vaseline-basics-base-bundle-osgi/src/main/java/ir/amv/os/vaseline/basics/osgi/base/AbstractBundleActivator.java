package ir.amv.os.vaseline.basics.osgi.base;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author Amir
 */
public abstract class AbstractBundleActivator implements BundleActivator {

    protected final Map<String, ServiceRegistration> serviceRegistrationMap = new Hashtable<>();
    protected final Map<String, ServiceListener> serviceListenerMap = new Hashtable<>();
    private BundleContext context;

    protected <S> void registerService(
            final Class<S> serviceClass,
            final S serviceImpl
    ) {
        registerService(serviceImpl.getClass().getSimpleName(), serviceClass, serviceImpl);
    }

    protected <S> void registerService(
            final String serviceName,
            final Class<S> serviceClass,
            final S serviceImpl
    ) {
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put("serviceName", serviceName);
        properties.put("implClass", serviceImpl.getClass().getName());
        ServiceRegistration<S> registration = context.registerService(serviceClass, serviceImpl, properties);
        serviceRegistrationMap.put(serviceName, registration);
    }

    protected void unregisterService(Class<?> serviceImplClass) {
        unregisterService(serviceImplClass.getSimpleName());
    }

    protected void unregisterService(String serviceName) {
        ServiceRegistration serviceRegistration = serviceRegistrationMap.remove(serviceName);
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }

    protected void addServiceListener(
            final ServiceListener listener,
            final String filter
    ) throws InvalidSyntaxException {
        addServiceListener(listener.getClass().getName(), listener, filter);
    }

    protected void addServiceListener(
            final String listenerName,
            final ServiceListener listener,
            final String filter
    ) throws InvalidSyntaxException {
        serviceListenerMap.put(listenerName, listener);
        context.addServiceListener(listener, filter);
    }

    @Override
    public void start(final BundleContext context) throws Exception {
        this.context = context;
        doStart(context);
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        serviceRegistrationMap.forEach((k, v) -> v.unregister());
        serviceListenerMap.forEach((k, v) -> context.removeServiceListener(v));
        doStop(context);
        this.context = null;
    }

    protected abstract void doStart(final BundleContext context) throws Exception;

    protected abstract void doStop(final BundleContext context) throws Exception;

}
