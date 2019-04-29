package ir.amv.os.vaseline.basics.logging.slf4j.osgi;

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.def.server.logger.IDefaultVaselineLogger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineLogger.class
)
public class VaselineLoggerSlf4jImpl
        implements IVaselineLogger, IDefaultVaselineLogger {

    private List<IVaselineLogCategorizer> logCategorizers;

    public VaselineLoggerSlf4jImpl() {
        logCategorizers = new ArrayList<>();
    }

    @Override
    public List<IVaselineLogCategorizer> getLogCategorizers() {
        return logCategorizers;
    }

    @Override
    public void doLog(final String loggerName, final VaselineLogLevel logLevel, final String log) {
        Logger logger = LoggerFactory.getLogger(loggerName);
        switch (logLevel) {
            case INFO:
                logger.info(log);
                break;
            case DEBUG:
                logger.debug(log);
                break;
            case ERROR:
                logger.error(log);
                break;
            case TRACE:
                logger.trace(log);
                break;
            case WARNING:
                logger.warn(log);
                break;
            default:
                logger.info(log);
        }
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
}