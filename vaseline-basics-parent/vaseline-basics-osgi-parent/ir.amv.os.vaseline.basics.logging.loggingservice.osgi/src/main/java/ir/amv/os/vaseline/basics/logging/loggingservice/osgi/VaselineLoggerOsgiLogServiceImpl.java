package ir.amv.os.vaseline.basics.logging.loggingservice.osgi;

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.def.server.logger.IDefaultVaselineLogger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.log.LogService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineLogger.class
)
public class VaselineLoggerOsgiLogServiceImpl
        implements IVaselineLogger, IDefaultVaselineLogger {

    private List<IVaselineLogCategorizer> logCategorizers;
    private LogService logService;

    public VaselineLoggerOsgiLogServiceImpl() {
        logCategorizers = new ArrayList<>();
    }

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
    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE
    )
    public void addLogCategorizer(final IVaselineLogCategorizer categorizer) {
        logCategorizers.add(categorizer);
    }

    @Override
    public void removeLogCategorizer(final IVaselineLogCategorizer categorizer) {
        logCategorizers.remove(categorizer);
    }

    @Reference
    public void setLogService(final LogService logService) {
        this.logService = logService;
    }

    public LogService getLogService() {
        return logService;
    }
}
