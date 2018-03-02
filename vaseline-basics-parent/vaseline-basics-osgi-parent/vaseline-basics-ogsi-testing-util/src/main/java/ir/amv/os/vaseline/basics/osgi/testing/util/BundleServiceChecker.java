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
    private int retryCount;

    public BundleServiceChecker(final String bundleSymbolicName, final List<Class<?>> bundleServices) {
        this(bundleSymbolicName, bundleServices, DEFAULT_BUNDLE_SERVICE_TIMEOUT);
    }

    public BundleServiceChecker(final String bundleSymbolicName, final List<Class<?>> bundleServices, final long timeout) {
        this(bundleSymbolicName, bundleServices, timeout, 1);
    }

    public BundleServiceChecker(final String bundleSymbolicName, final List<Class<?>> bundleServices, final long timeout, final int retryCount) {
        super(bundleSymbolicName, timeout);
        this.bundleServices = bundleServices;
        this.retryCount = retryCount;
    }

    @Override
    protected void checkAfterBundleStarted(final Bundle bundle) {
        for (Class<?> bundleService : bundleServices) {
            logInfo(String.format("checking if service '%s' is registered", bundleService));
            boolean found = false;
            for (int i = 0; i < retryCount; i++) {
                logInfo(String.format("Attempt %d to find %s", i+1, bundleService));
                ServiceReference<?>[] registeredServices = bundle.getRegisteredServices();
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
                    break;
                } else try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (found) {
                logInfo(String.format("service %s is exported by %s", bundleService, bundleSymbolicName));
            } else {
                logError(String.format("can not find service '%s'", bundleService));
                throw new IllegalStateException("Can not find service " + bundleService);
            }
        }
    }
}
