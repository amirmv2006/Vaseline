package ir.amv.os.vaseline.basics.osgi.logging.osgiloggingservice.server.logger;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.apis.loggingimpl.server.logger.IImplementedVaselineLogger;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * @author Amir
 */
public class VaselineLoggerOsgiLogServiceImpl
        implements IImplementedVaselineLogger {

    private List<IVaselineLogCategorizer> logCategorizers;
    private ServiceTracker<LogService, LogService> logServiceTracker;

    @Override
    public void doLog(final String loggerName, final VaselineLogLevel logLevel, final String log) {
        switch (logLevel) {
            case INFO:
                getLogService().log(LogService.LOG_INFO, log);
                break;
            case DEBUG:
                getLogService().log(LogService.LOG_DEBUG, log);
                break;
            case ERROR:
                getLogService().log(LogService.LOG_ERROR, log);
                break;
            case TRACE:
                getLogService().log(LogService.LOG_DEBUG, log);
                break;
            case WARNING:
                getLogService().log(LogService.LOG_WARNING, log);
                break;
            default:
                getLogService().log(LogService.LOG_INFO, log);
        }
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

    public void setLogServiceTracker(final ServiceTracker<LogService, LogService> logServiceTracker) {
        this.logServiceTracker = logServiceTracker;
    }

    public LogService getLogService() {
        return logServiceTracker.getService();
    }
}
