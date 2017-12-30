package ir.amv.os.vaseline.basics.osgi.logging.common.activator;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.osgi.base.AbstractBundleActivator;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.categorizer.DefaultVaselineLogCategorizerOsgiServiceImpl;
import org.osgi.framework.BundleContext;

/**
 * @author Amir
 */
public class VaselineCommonLoggingActivator extends AbstractBundleActivator {

    private VaselineLogServiceLoggerCategorizerTracker loggerCategorizerTracker;

    @Override
    public void doStart(final BundleContext context) throws Exception {
        loggerCategorizerTracker = new VaselineLogServiceLoggerCategorizerTracker(context);
        loggerCategorizerTracker.open();
        DefaultVaselineLogCategorizerOsgiServiceImpl logCategorizer = new DefaultVaselineLogCategorizerOsgiServiceImpl();
        registerService(IVaselineLogCategorizer.class, logCategorizer);
    }

    @Override
    public void doStop(final BundleContext context) throws Exception {
        loggerCategorizerTracker.close();
    }
}
