package ir.amv.os.vaseline.basics.testing.osgi.util;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Amir
 */
public abstract class AbstractBundleChecker {
    protected static Logger LOGGER;

    protected String bundleSymbolicName;
    protected long timeout;

    public AbstractBundleChecker(final String bundleSymbolicName, final long timeout) {
        if (LOGGER == null) {
            LOGGER = LoggerFactory.getLogger(getClass());
        }
        this.bundleSymbolicName = bundleSymbolicName;
        this.timeout = timeout;
    }

    public void checkBundle(BundleContext bundleContext) throws InterruptedException {
        long mainStartTime = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        boolean bundleStarted = false;
        while (!bundleStarted &&
                System.currentTimeMillis() - startTime < timeout &&
                System.currentTimeMillis() - mainStartTime < 180_000) { // 3 minutes
            Bundle[] bundles = bundleContext.getBundles();
            for (Bundle bundle : bundles) {
                if (bundle.getSymbolicName().toLowerCase().contains(bundleSymbolicName)) {
                    logInfo(String.format("bundle %s started", bundleSymbolicName));
                    bundleStarted = true;
                    checkAfterBundleStarted(bundle);
                }
            }
            if (!bundleStarted) {
                startTime = System.currentTimeMillis(); // timeout should be calculated from the time bundle has
                // actually started
            } else {
                break;
            }
            Thread.sleep(1000L);
        }
        logInfo(String.format("checking bundle %s startup took %s milliseconds", bundleSymbolicName, System
                .currentTimeMillis() - startTime));
        if (!bundleStarted) {
            logError(String.format("bundle not started '%s'", bundleSymbolicName));
            throw new IllegalStateException("Bundle not started " + bundleSymbolicName);
        }
    }

    protected abstract void checkAfterBundleStarted(final Bundle bundle);

    protected void logError(String message) {
        System.err.println(message); // log won't show in console
        LOGGER.error(message);
    }

    protected void logInfo(final String message) {
        System.out.println(message); // log won't show in console
        LOGGER.info(message);
    }

}
