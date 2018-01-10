package ir.amv.os.vaseline.basics.osgi.logging.common.activator;

import ir.amv.os.vaseline.basics.osgi.base.AbstractBundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Amir
 */
public class VaselineCommonLoggingActivator extends AbstractBundleActivator {

    private VaselineLoggerTracker loggerCategorizerTracker;

    @Override
    public void doStart(final BundleContext context) throws Exception {
        loggerCategorizerTracker = new VaselineLoggerTracker(context);
        loggerCategorizerTracker.open();
    }

    @Override
    public void doStop(final BundleContext context) throws Exception {
        loggerCategorizerTracker.close();
    }
}
