package ir.amv.os.vaseline.basics.osgi.logging.pax.logging.activator;

import ir.amv.enterprise.osgi.bundle.base.api.activator.AbstractBundleActivator;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.logging.pax.logging.server.logger.VaselineLoggerPaxLogServiceImpl;
import org.ops4j.pax.logging.PaxLoggingService;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import java.util.ArrayList;

/**
 * @author Amir
 */
public class VaselineBasicLoggingPaxLoggerActivator extends AbstractBundleActivator {

    private ServiceTracker<PaxLoggingService, PaxLoggingService> logServiceTracker;

    @Override
    public void doStart(final BundleContext context) throws Exception {
        logServiceTracker = new ServiceTracker<>(context, context
                .createFilter("(objectClass=" + PaxLoggingService.class.getName() + ")"), null);
        logServiceTracker.open();
        VaselineLoggerPaxLogServiceImpl vaselineLogger = new VaselineLoggerPaxLogServiceImpl();
        vaselineLogger.setLogCategorizers(new ArrayList<>());
        vaselineLogger.setLogServiceTracker(logServiceTracker);
        registerService(IVaselineLogger.class, vaselineLogger);
    }

    @Override
    public void doStop(final BundleContext context) throws Exception {
        logServiceTracker.close();
    }
}
