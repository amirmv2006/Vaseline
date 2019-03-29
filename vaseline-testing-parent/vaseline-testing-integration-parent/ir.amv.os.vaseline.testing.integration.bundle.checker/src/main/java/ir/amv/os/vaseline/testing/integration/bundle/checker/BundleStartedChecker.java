package ir.amv.os.vaseline.testing.integration.bundle.checker;

import org.osgi.framework.Bundle;

/**
 * @author Amir
 */
public class BundleStartedChecker extends AbstractBundleChecker {

    private static final long DEFAULT_BUNDLE_START_TIMEOUT = 5000;

    public BundleStartedChecker(final String bundleSymbolicName) {
        this(bundleSymbolicName, DEFAULT_BUNDLE_START_TIMEOUT);
    }

    public BundleStartedChecker(final String bundleSymbolicName, final long timeout) {
        super(bundleSymbolicName, timeout);
    }

    @Override
    protected void checkAfterBundleStarted(final Bundle bundle) {
        // do nothing
    }

}
