package ir.amv.os.vaseline.basics.osgi.testing.util;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

import java.util.List;

/**
 * @author Amir
 */
public class BundleServiceChecker extends AbstractBundleChecker {

    private static final long DEFAULT_BUNDLE_SERVICE_TIMEOUT = 5000;
    private List<Class<?>> bundleServices;

    public BundleServiceChecker(final String bundleSymbolicName, final List<Class<?>> bundleServices) {
        this(bundleSymbolicName, bundleServices, DEFAULT_BUNDLE_SERVICE_TIMEOUT);
    }

    public BundleServiceChecker(final String bundleSymbolicName, final List<Class<?>> bundleServices, final long timeout) {
        super(bundleSymbolicName, timeout);
        this.bundleServices = bundleServices;
    }

    @Override
    protected void checkAfterBundleStarted(final Bundle bundle) {
        ServiceReference<?>[] registeredServices = bundle.getRegisteredServices();
        for (Class<?> bundleService : bundleServices) {
            logInfo(String.format("checking if service '%s' is registered", bundleService));
            boolean found = false;
            for (ServiceReference<?> registeredService : registeredServices) {
                String[] objectClasses = (String[]) registeredService.getProperty("objectClass");
                for (String objectClass : objectClasses) {
                    if (bundleService.getName().equals(objectClass)) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                logInfo(String.format("service %s is exported by %s", bundleService, bundleSymbolicName));
            } else {
                logError(String.format("can not find service '%s'", bundleService));
                throw new IllegalStateException("Can not find service " + bundleServices);
            }
        }
    }
}
