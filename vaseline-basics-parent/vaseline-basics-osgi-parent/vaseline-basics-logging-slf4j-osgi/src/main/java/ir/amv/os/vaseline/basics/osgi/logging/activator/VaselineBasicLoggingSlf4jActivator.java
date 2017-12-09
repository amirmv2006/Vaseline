package ir.amv.os.vaseline.basics.osgi.logging.activator;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.logging.server.categorizer.DefaultVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.osgi.logging.server.logger.VaselineLoggerSlf4jImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;

import java.util.Hashtable;

/**
 * @author Amir
 */
public class VaselineBasicLoggingSlf4jActivator implements BundleActivator {

    private DefaultVaselineLogCategorizer logCategorizer;
    private VaselineLoggerSlf4jImpl loggerSlf4j;

    @Override
    public void start(final BundleContext context) throws Exception {
        createLoggerSlf4jImpl(context);
        createDefaultLogCategorizerBean(context);
    }

    public void createLoggerSlf4jImpl(final BundleContext context) throws InvalidSyntaxException {
        loggerSlf4j = new VaselineLoggerSlf4jImpl();
        context.addServiceListener(event -> {
            switch (event.getType()) {
                case ServiceEvent.MODIFIED:
                case ServiceEvent.MODIFIED_ENDMATCH:
                case ServiceEvent.REGISTERED:
                    addLogCategorizer(getiVaselineLogCategorizerFromEvent(event, context));
                    break;
                case ServiceEvent.UNREGISTERING:
                    removeLogCategorizer(getiVaselineLogCategorizerFromEvent(event, context));
                    break;

            }
        }, "(objectClass=" + IVaselineLogCategorizer.class + ")");
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put("implClass", loggerSlf4j.getClass().getName());
        context.registerService(IVaselineLogger.class, loggerSlf4j, properties);
    }

    public void createDefaultLogCategorizerBean(final BundleContext context) {
        logCategorizer = new DefaultVaselineLogCategorizer();
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put("implClass", logCategorizer.getClass().getName());
        context.registerService(IVaselineLogCategorizer.class, logCategorizer, properties);
    }

    public IVaselineLogCategorizer getiVaselineLogCategorizerFromEvent(final ServiceEvent event, final BundleContext context) {
        ServiceReference<IVaselineLogCategorizer> serviceReference = (ServiceReference<IVaselineLogCategorizer>) event.getServiceReference();
        return context.getService(serviceReference);
    }

    public void addLogCategorizer(final IVaselineLogCategorizer logCategorizer) {
        loggerSlf4j.getLogCategorizers().add(logCategorizer);
    }

    public void removeLogCategorizer(final IVaselineLogCategorizer logCategorizer) {
        loggerSlf4j.getLogCategorizers().remove(logCategorizer);
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        logCategorizer = null;
    }
}
