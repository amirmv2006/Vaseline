package ir.amv.os.vaseline.basics.spring.logging.server.logger;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.apis.loggingimpl.server.logger.IImplementedVaselineLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Amir
 */
@Component
public class VaselineLoggerSlf4jImpl
        implements IImplementedVaselineLogger {

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
}
