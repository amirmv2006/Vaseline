package ir.amv.os.vaseline.basics.apis.logging.server.logger;

import ir.amv.os.vaseline.basics.apis.logging.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogException;

/**
 * @author Amir
 */
public interface IVaselineLogger {

    boolean isLogLevelEnabled(VaselineLogLevel logLevel);

    /**
     * method to log
     * @param source the source that is performing the log, usually the name of the class that in performing the log
     * @param category category for the log, so far two categories come to my mind, business and technical
     * @param logLevel the level for the log
     * @param formattedMessage the formatted log message (String.format), won't be formatted if args is null
     * @param args arguments for the formatted message
     */
    void log(String source, String category, VaselineLogLevel logLevel, String formattedMessage, Object... args) throws LogException;

    String toLogString(Object object);

    void addLogCategorizer(IVaselineLogCategorizer categorizer);
    void removeLogCategorizer(IVaselineLogCategorizer categorizer);
}
