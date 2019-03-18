package ir.amv.os.vaseline.basics.logging.slf4j.osgi;

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.def.server.logger.IDefaultVaselineLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Amir
 */
public class VaselineLoggerSlf4jImpl
        implements IDefaultVaselineLogger {

    private List<IVaselineLogCategorizer> logCategorizers;

    @Override
    public List<IVaselineLogCategorizer> getLogCategorizers() {
        return logCategorizers;
    }

    @Autowired
    public void setLogCategorizers(final List<IVaselineLogCategorizer> logCategorizers) {
        this.logCategorizers = logCategorizers;
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
    public void addLogCategorizer(final IVaselineLogCategorizer categorizer) {
        logCategorizers.add(categorizer);
    }

    @Override
    public void removeLogCategorizer(final IVaselineLogCategorizer categorizer) {
        logCategorizers.remove(categorizer);
    }
}
