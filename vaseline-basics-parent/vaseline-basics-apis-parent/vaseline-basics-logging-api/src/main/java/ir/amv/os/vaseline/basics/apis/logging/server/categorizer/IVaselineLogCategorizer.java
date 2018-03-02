package ir.amv.os.vaseline.basics.apis.logging.server.categorizer;

import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogException;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;

/**
 * @author Amir
 */
public interface IVaselineLogCategorizer {

    int prioirity();
    String getLoggerFor(String name, String category);
    String prepareLog(final String source, final String category, final VaselineLogLevel logLevel, final String
            formattedMessage, final Object... args) throws LogException;
}
