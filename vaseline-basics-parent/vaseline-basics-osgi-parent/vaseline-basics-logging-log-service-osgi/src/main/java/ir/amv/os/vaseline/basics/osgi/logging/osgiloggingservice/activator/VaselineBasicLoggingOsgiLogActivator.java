package ir.amv.os.vaseline.basics.osgi.logging.osgiloggingservice.activator;

import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.base.AbstractBundleActivator;
import ir.amv.os.vaseline.basics.osgi.logging.osgiloggingservice.server.logger.VaselineLoggerOsgiLogServiceImpl;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

import java.util.ArrayList;

/**
 * @author Amir
 */
public class VaselineBasicLoggingOsgiLogActivator extends AbstractBundleActivator {

    private ServiceTracker<LogService, LogService> logServiceTracker;

    @Override
    public void doStart(final BundleContext context) throws Exception {
        logServiceTracker = new ServiceTracker<>(context, context
                .createFilter("(objectClass=" + LogService.class.getName() + ")"), null);
        logServiceTracker.open();
        VaselineLoggerOsgiLogServiceImpl vaselineLogger = new VaselineLoggerOsgiLogServiceImpl();
        vaselineLogger.setLogCategorizers(new ArrayList<>());
        vaselineLogger.setLogServiceTracker(logServiceTracker);
        registerService(IVaselineLogger.class, vaselineLogger);
    }

    @Override
    public void doStop(final BundleContext context) throws Exception {
        logServiceTracker.close();
    }

}
