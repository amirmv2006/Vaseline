package ir.amv.os.vaseline.basics.apis.loggingimpl.server.logger;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogException;
import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogInterruptOthersException;
import ir.amv.os.vaseline.basics.apis.logging.server.obj.ILogObject;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amir
 */
public interface IImplementedVaselineLogger
        extends IVaselineLogger {

    String ELEMENT_SEPARATOR = ", ";

    List<IVaselineLogCategorizer> getLogCategorizers();
    void doLog(String loggerName, final VaselineLogLevel logLevel, String log);

    @Override
    default boolean isLogLevelEnabled(VaselineLogLevel logLevel) {
        return true;
    }

    @Override
    default void log(String source, String category, VaselineLogLevel logLevel, String formattedMessage, Object... args) throws LogException {
        List<IVaselineLogCategorizer> logCategorizers = new ArrayList<>(getLogCategorizers());
        logCategorizers.sort((Comparator.comparingInt(IVaselineLogCategorizer::prioirity)));
        for (IVaselineLogCategorizer logCategorizer : logCategorizers) {
            String log;
            boolean interrupt = false;
            try {
                log = logCategorizer.prepareLog(source, category, logLevel, formattedMessage, args);
            } catch (LogInterruptOthersException ignored) {
                interrupt = true;
                log = ignored.getLogMessage();
            }
            if (log != null) {
                doLog(logCategorizer.getLoggerFor(source, category), logLevel, log);
            }
            if (interrupt) {
                break;
            }
        }
    }

    @Override
    default String toLogString(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof Iterable) {
            Iterable iterable = (Iterable) object;
            StringBuilder sb = new StringBuilder("[");
            for (Object o : iterable) {
                sb.append(toLogString(o));
                sb.append(ELEMENT_SEPARATOR);
            }
            return sb.substring(0, sb.length() - ELEMENT_SEPARATOR.length()) + "]";
        }
        if (object instanceof Map) {
            Map map = (Map) object;
            StringBuilder sb = new StringBuilder("{");
            Set<Map.Entry> set = map.entrySet();
            for (Map.Entry entry : set) {
                sb.append("\"").append(toLogString(entry.getKey())).append("\"");
                sb.append(":");
                sb.append("\"").append(toLogString(entry.getValue())).append("\"");
                sb.append(ELEMENT_SEPARATOR);
            }
            return sb.substring(0, sb.length() - ELEMENT_SEPARATOR.length()) + "}";
        }
        if (object instanceof ILogObject) {
            ILogObject logObject = (ILogObject) object;
            return logObject.toLogString();
        }
        return object.toString();// maybe change this to json (there might be a loop)?
    }
}
