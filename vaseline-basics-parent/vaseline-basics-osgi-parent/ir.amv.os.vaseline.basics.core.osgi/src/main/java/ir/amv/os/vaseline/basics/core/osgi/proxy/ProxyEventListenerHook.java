package ir.amv.os.vaseline.basics.core.osgi.proxy;

import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyAware;
import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyInterceptor;
import org.apache.aries.proxy.ProxyManager;
import org.apache.aries.proxy.UnableToProxyException;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.hooks.service.EventListenerHook;
import org.osgi.framework.hooks.service.ListenerHook;
import org.osgi.service.component.annotations.*;

import java.util.*;

import static ir.amv.os.vaseline.basics.core.osgi.constants.VaselineCoreOsgiConstants.NEEDS_PROXY;
import static org.osgi.framework.ServiceEvent.*;

@Component(
        immediate = true,
        service = EventListenerHook.class
)
public class ProxyEventListenerHook implements EventListenerHook {
    static final String PROXY = "PROXY";

    private BundleContext bc;
    private ProxyManager proxyManager;
    private List<IProxyInterceptor> interceptors = new ArrayList<>();

    @Activate
    public void init(BundleContext bundleContext) {
        bc = bundleContext;
    }

    @Override
    public void event(ServiceEvent serviceEvent, Map<BundleContext, Collection<ListenerHook.ListenerInfo>> map) {
        ServiceReference serviceReference = serviceEvent.getServiceReference();

        if (serviceReference.getProperty(NEEDS_PROXY) != null &&
                (Boolean) serviceReference.getProperty(NEEDS_PROXY)&&
                serviceReference.getProperty(PROXY) == null &&
                serviceReference.getBundle().getBundleContext() != bc) {
            Bundle bundle = serviceReference.getBundle();
            try {
                switch (serviceEvent.getType()) {
                    case REGISTERED: {
                        registerProxiedService(bundle, serviceReference);
                        break;
                    }
                    case UNREGISTERING: {
                        //TODO
                        break;
                    }
                    case MODIFIED:
                    case MODIFIED_ENDMATCH: {
                        //TODO
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void registerProxiedService(
            final Bundle bundle,
            final ServiceReference<?> serviceReference) throws ClassNotFoundException, UnableToProxyException {
        Dictionary<String, Object> properties = buildProps(serviceReference);
        String[] interfaces = (String[]) serviceReference.getProperty(
                "objectClass");
        Class[] toClass = toClass(interfaces, bundle);
        properties.put(PROXY, true);
        Object object = bc.getService(serviceReference);
        Object proxy = proxyManager.createDelegatingInterceptingProxy(
                bundle,
                Arrays.asList(toClass),
                () -> object,
                null,
                new ProxyInterceptor(object, this)
        );
//        Object proxy = Proxy.newProxyInstance(toClass[0].getClassLoader(), toClass, new ProxyInterceptor(bc, serviceReference));
        if (object instanceof IProxyAware) {
            IProxyAware proxyAware = (IProxyAware) object;
            proxyAware.setProxy(proxy);
        }
        bundle.getBundleContext().registerService(interfaces, proxy, properties);
    }

    private Class[] toClass(final String[] interfaces, final Bundle bundle) throws ClassNotFoundException {
        Class[] result = new Class[interfaces.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = bundle.loadClass(interfaces[i]);
        }
        return result;
    }

    private Dictionary<String, Object> buildProps(final ServiceReference sr) {
        String[] propertyKeys = sr.getPropertyKeys();
        Dictionary<String, Object> result = new Hashtable<>();
        if (propertyKeys != null) {
            for (String propertyKey : propertyKeys) {
                result.put(propertyKey, sr.getProperty(propertyKey));
            }
        }
        return result;
    }

    public List<IProxyInterceptor> getProxyInterceptors() {
        return interceptors;
    }

    @Reference
    public void setProxyManager(final ProxyManager proxyManager) {
        this.proxyManager = proxyManager;
    }

    @Reference(
            cardinality = ReferenceCardinality.MULTIPLE,
            policyOption = ReferencePolicyOption.GREEDY
    )
    public void addProxyInterceptor(final IProxyInterceptor interceptor) {
        if (!interceptors.contains(interceptor)) {
            interceptors.add(interceptor);
            interceptors.sort(Comparator.<IProxyInterceptor, Integer>
                    comparing(IProxyInterceptor::priority).reversed());
        }
    }

    public void removeProxyInterceptor(final IProxyInterceptor interceptor) {
        interceptors.remove(interceptor);
    }

}
