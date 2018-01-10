package ir.amv.os.vaseline.basics.osgi.logging.common.activator;

import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amir
 */
public class VaselineLoggerTracker extends
        ServiceTracker<IVaselineLogger, IVaselineLogger> {

    public VaselineLoggerTracker(final BundleContext context) {
        super(context, IVaselineLogger.class.getName(), null);
    }

    @Override
    public IVaselineLogger addingService(final ServiceReference<IVaselineLogger> reference) {
        IVaselineLogger vaselineLogger = super.addingService(reference);
        LOGGER.setVaselineLogger(vaselineLogger);
        return vaselineLogger;
    }

    @Override
    public void removedService(final ServiceReference<IVaselineLogger> reference, final IVaselineLogger service) {
        LOGGER.setVaselineLogger(null);
        super.removedService(reference, service);
    }
}
