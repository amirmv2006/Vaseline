package ir.amv.os.vaseline.basics.osgi.logging.pax.logging.server.logger;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogException;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.apis.loggingimpl.server.logger.IImplementedVaselineLogger;
import org.ops4j.pax.logging.PaxLogger;
import org.ops4j.pax.logging.PaxLoggingService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * @author Amir
 */
public class VaselineLoggerPaxLogServiceImpl
        implements IImplementedVaselineLogger {

    private List<IVaselineLogCategorizer> logCategorizers;
    private ServiceTracker<PaxLoggingService, PaxLoggingService> logServiceTracker;

    @Override
    public void doLogWithCategory(final String loggerName, final String category, final VaselineLogLevel logLevel, final String
            logMessage) {
        PaxLogger logger = getLogger(loggerName, category);
        switch (logLevel) {
            case INFO:
                logger.inform(logMessage, null);
                break;
            case DEBUG:
                logger.debug(logMessage, null);
                break;
            case ERROR:
                logger.error(logMessage, null);
                break;
            case TRACE:
                logger.debug(logMessage, null);
                break;
            case WARNING:
                logger.warn(logMessage, null);
                break;
            default:
                logger.inform(logMessage, null);
        }
    }

    @Override
    public void doLog(final String loggerName, final VaselineLogLevel logLevel, final String log) {
        throw new LogException("NOT SUPPORTED");
    }

    @Override
    public List<IVaselineLogCategorizer> getLogCategorizers() {
        return logCategorizers;
    }

    @Override
    public void addLogCategorizer(final IVaselineLogCategorizer categorizer) {
        logCategorizers.add(categorizer);
    }

    @Override
    public void removeLogCategorizer(final IVaselineLogCategorizer categorizer) {
        logCategorizers.remove(categorizer);
    }

    public void setLogCategorizers(final List<IVaselineLogCategorizer> logCategorizers) {
        this.logCategorizers = logCategorizers;
    }

    public void setLogServiceTracker(final ServiceTracker<PaxLoggingService, PaxLoggingService> logServiceTracker) {
        this.logServiceTracker = logServiceTracker;
    }

    public PaxLogger getLogger(final String loggerName, final String category) {
        PaxLoggingService loggingService = logServiceTracker.getService();
        Bundle bundle;
        String paxLogCategory;
        try {
            Class<?> aClass = Class.forName(loggerName);
            paxLogCategory = aClass.getSimpleName() + "[" + category + "]";
            bundle = FrameworkUtil.getBundle(aClass);
        } catch (ClassNotFoundException e) {
            bundle = FrameworkUtil.getBundle(getClass());
            paxLogCategory = category;
        }
        return loggingService.getLogger(bundle, paxLogCategory, loggerName);
    }
}
