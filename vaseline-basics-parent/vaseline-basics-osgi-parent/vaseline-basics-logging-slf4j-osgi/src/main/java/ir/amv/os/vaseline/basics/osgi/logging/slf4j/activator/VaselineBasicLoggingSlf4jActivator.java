package ir.amv.os.vaseline.basics.osgi.logging.slf4j.activator;

import ir.amv.enterprise.osgi.bundle.base.api.activator.AbstractBundleActivator;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.osgi.logging.slf4j.server.logger.VaselineLoggerSlf4jImpl;
import org.osgi.framework.BundleContext;

import java.util.ArrayList;

/**
 * @author Amir
 */
public class VaselineBasicLoggingSlf4jActivator extends AbstractBundleActivator {

    @Override
    public void doStart(final BundleContext context) throws Exception {
        VaselineLoggerSlf4jImpl vaselineLogger = new VaselineLoggerSlf4jImpl();
        vaselineLogger.setLogCategorizers(new ArrayList<>());
        registerService(IVaselineLogger.class, vaselineLogger);
    }

    @Override
    public void doStop(final BundleContext context) throws Exception {
    }
}
