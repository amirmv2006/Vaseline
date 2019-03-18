package ir.amv.os.vaseline.basics.logging.api.server.categorizer;

import ir.amv.os.vaseline.basics.logging.api.server.exc.LogException;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;

/**
 * @author Amir
 */
public interface IVaselineLogCategorizer {

    int priority();
    String getLoggerFor(String name, String category);
    String prepareLog(final String source, final String category, final VaselineLogLevel logLevel, final String
            formattedMessage, final Object... args) throws LogException;
}
