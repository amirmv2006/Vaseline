package ir.amv.os.vaseline.basics.logging.pax.osgi;

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.api.server.exc.LogException;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.def.server.logger.IDefaultVaselineLogger;
import org.ops4j.pax.logging.PaxLogger;
import org.ops4j.pax.logging.PaxLoggingService;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineLogger.class
)
public class VaselineLoggerPaxLogServiceImpl
        implements IVaselineLogger, IDefaultVaselineLogger {

    private List<IVaselineLogCategorizer> logCategorizers;
    private PaxLoggingService loggingService;

    public VaselineLoggerPaxLogServiceImpl() {
        logCategorizers = new ArrayList<>();
    }

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
    public void setLoggingService(final PaxLoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public PaxLogger getLogger(final String loggerName, final String category) {
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
