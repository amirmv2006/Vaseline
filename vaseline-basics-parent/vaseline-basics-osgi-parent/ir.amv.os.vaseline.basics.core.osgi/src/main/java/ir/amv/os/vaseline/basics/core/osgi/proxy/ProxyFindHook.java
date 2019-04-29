package ir.amv.os.vaseline.basics.core.osgi.proxy;

import ir.amv.os.vaseline.basics.core.osgi.constants.VaselineCoreOsgiConstants;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.hooks.service.FindHook;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.Collection;
import java.util.Iterator;

@Component(
        immediate = true,
        service = FindHook.class
)
public class ProxyFindHook implements FindHook {
    private BundleContext bc;

    @Activate
    public void init(BundleContext bundleContext) {
        bc = bundleContext;
    }

    @Override
    public void find(BundleContext context, String name, String filter, boolean allServices, Collection<ServiceReference<?>> references) {
        try {
            if (this.bc.equals(context) || context.getBundle().getBundleId() == 0) {
                return;
            }

            Iterator iterator = references.iterator();

            while (iterator.hasNext()) {
                ServiceReference sr = (ServiceReference) iterator.next();

                if (sr.getProperty(VaselineCoreOsgiConstants.NEEDS_PROXY) != null &&
                        sr.getProperty(ProxyEventListenerHook.PROXY) == null) {
                    iterator.remove();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
