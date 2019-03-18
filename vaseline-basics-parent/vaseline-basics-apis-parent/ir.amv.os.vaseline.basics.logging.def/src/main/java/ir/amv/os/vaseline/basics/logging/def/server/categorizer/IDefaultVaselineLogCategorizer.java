package ir.amv.os.vaseline.basics.logging.def.server.categorizer;

import ir.amv.os.vaseline.basics.logging.api.server.categorizer.IVaselineLogCategorizer;
import ir.amv.os.vaseline.basics.logging.api.server.exc.LogException;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;

/**
 * @author Amir
 */
public interface IDefaultVaselineLogCategorizer
        extends IVaselineLogCategorizer {

    @Override
    default int priority() {
        return Integer.MAX_VALUE;
    }

    @Override
    default String getLoggerFor(String name, String category) {
        return name;
    }

    @Override
    default String prepareLog(
            final String source,
            final String category,
            final VaselineLogLevel logLevel,
            final String formattedMessage,
            final Object... args) throws LogException {
        return (args == null) ? formattedMessage : String.format(formattedMessage, args);
    }
}
