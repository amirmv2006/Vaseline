package ir.amv.os.vaseline.basics.osgi.logging.common.server.helper;

import ir.amv.os.vaseline.basics.apis.logging.server.constants.LoggerConstants;
import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogException;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * a class for holding static reference to {@link ir.amv.os.vaseline.basics.apis.logging.server.logger.IVaselineLogger}
 * @author Amir
 */
public class LOGGER {

    private static IVaselineLogger vaselineLogger;
    private static Queue<LogMessage> logMessageQueue = new LinkedList<>();

    private LOGGER() {
    }

    public static synchronized void setVaselineLogger(final IVaselineLogger vaselineLogger) {
        LOGGER.vaselineLogger = vaselineLogger;
        if (vaselineLogger != null) {
            deque();
        }
    }

    private static synchronized void deque() {
        logMessageQueue.forEach(logMessage -> {
            try {
                log(logMessage.source, logMessage.category, logMessage.logLevel,
                        logMessage.formattedMessage, logMessage.args);
            } catch (LogException e) {
                Logger.getLogger("FATAL_LOG_PROBLEM").log(Level.SEVERE, e, () -> "Log exception while logging!");
            }
        });
    }

    public static void log(String formattedMessage, Object... args) throws LogException {
        log(VaselineLogLevel.INFO, formattedMessage, args);
    }

    public static void log(VaselineLogLevel logLevel, String formattedMessage, Object... args) throws LogException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 2) {
            throw new LogException("Can not get stack trace");
        }
        String source = stackTrace[2].getClassName();
        log(source, logLevel, formattedMessage, args);
    }

    public static void log(String source, String formattedMessage, Object... args) throws LogException {
        log(source, VaselineLogLevel.INFO, formattedMessage, args);
    }

    public static void log(String source, VaselineLogLevel logLevel, String formattedMessage, Object... args) throws LogException {
        log(source, LoggerConstants.TECHNICAL_LOG, logLevel, formattedMessage, args);
    }

    public static synchronized void log(String source, String category, VaselineLogLevel logLevel, String formattedMessage,
                            Object... args) throws LogException {
        if (vaselineLogger == null) {
            logMessageQueue.add(new LogMessage(source, category, logLevel, formattedMessage, args));
        } else {
            vaselineLogger.log(source, category, logLevel, formattedMessage, args);
        }
    }

    private static class LogMessage {
        private String source;
        private String category;
        private VaselineLogLevel logLevel;
        private String formattedMessage;
        private Object[] args;

        public LogMessage(final String source, final String category, final VaselineLogLevel logLevel, final String formattedMessage, final Object[] args) {
            this.source = source;
            this.category = category;
            this.logLevel = logLevel;
            this.formattedMessage = formattedMessage;
            this.args = args;
        }
    }
}
