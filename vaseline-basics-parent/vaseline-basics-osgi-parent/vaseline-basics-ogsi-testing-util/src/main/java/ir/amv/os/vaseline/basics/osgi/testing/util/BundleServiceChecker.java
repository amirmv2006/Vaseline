package ir.amv.os.vaseline.basics.osgi.testing.util;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Amir
 */
public class BundleServiceChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(BundleServiceChecker.class);

    private String bundleSymbolicName;
    private List<Class<?>> bundleServices;
    private long timeout;

    public BundleServiceChecker(final String bundleSymbolicName, final List<Class<?>> bundleServices, final long timeout) {
        this.bundleSymbolicName = bundleSymbolicName;
        this.bundleServices = bundleServices;
        this.timeout = timeout;
    }

    public void checkForRegisteredServices(BundleContext bundleContext) throws ClassNotFoundException, InterruptedException {
        long startTime = System.currentTimeMillis();
        boolean bundleStarted = false;
        while (!bundleStarted && System.currentTimeMillis() - startTime < timeout) {
            Bundle[] bundles = bundleContext.getBundles();
            for (Bundle bundle : bundles) {
                if (bundle.getSymbolicName().toLowerCase().contains(bundleSymbolicName)) {
                    logInfo(String.format("bundle %s started", bundleSymbolicName));
                    bundleStarted = true;
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
            Thread.sleep(1000L);
        }
        logInfo(String.format("checking bundle %s took %s milliseconds", bundleSymbolicName, System.currentTimeMillis
                () - startTime));
    }

    public void logError(String message) {
        System.err.println(message); // log won't show in console
        LOGGER.error(message);
    }

    public void logInfo(final String message) {
        System.out.println(message); // log won't show in console
        LOGGER.info(message);
    }
}
