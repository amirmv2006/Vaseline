package ir.amv.os.vaseline.basics.osgi.logging.common.activator;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amir
 */
public class VaselineLogServiceLoggerCategorizerTracker
        extends ServiceTracker<IVaselineLogCategorizer, IVaselineLogger> {

    private VaselineLoggerServiceTracker vaselineLoggerServiceTracker;

    public VaselineLogServiceLoggerCategorizerTracker(final BundleContext context) throws InvalidSyntaxException {
        super(context, context.createFilter("(objectClass=" + IVaselineLogCategorizer.class.getName() + ")"), null);
        vaselineLoggerServiceTracker = new VaselineLoggerServiceTracker(context);
    }

    @Override
    public void open() {
        super.open();
        vaselineLoggerServiceTracker.open();
    }

    @Override
    public synchronized IVaselineLogger addingService(final ServiceReference<IVaselineLogCategorizer> reference) {
        IVaselineLogger vaselineLogger = vaselineLoggerServiceTracker.getService();
        IVaselineLogCategorizer categorizer = context.getService(reference);
        vaselineLogger.addLogCategorizer(categorizer);
        return vaselineLogger;
    }

    @Override
    public synchronized void removedService(final ServiceReference<IVaselineLogCategorizer> reference, final IVaselineLogger
            service) {
        IVaselineLogger vaselineLogger = vaselineLoggerServiceTracker.getService();
        IVaselineLogCategorizer categorizer = context.getService(reference);
        vaselineLogger.removeLogCategorizer(categorizer);
    }

    @Override
    public void close() {
        vaselineLoggerServiceTracker.close();
        super.close();
    }

    private static class VaselineLoggerServiceTracker extends ServiceTracker<IVaselineLogger, IVaselineLogger> {

        private VaselineLoggerServiceTracker(final BundleContext context) throws InvalidSyntaxException {
            super(context, context.createFilter("(objectClass=" + IVaselineLogger.class.getName() + ")"), null);
        }

        @Override
        public IVaselineLogger addingService(final ServiceReference<IVaselineLogger> reference) {
            IVaselineLogger vaselineLogger = super.addingService(reference);
            LOGGER.setVaselineLogger(vaselineLogger);
            return vaselineLogger;
        }
    }
}
