package ir.amv.os.vaseline.security.audit.def.log.server.categorizer;

import ir.amv.os.vaseline.basics.logging.api.server.exc.LogException;
import ir.amv.os.vaseline.basics.logging.api.server.exc.LogInterruptOthersException;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.def.server.categorizer.IDefaultVaselineLogCategorizer;
import ir.amv.os.vaseline.security.audit.def.log.server.basic.IImplementedAuditLogApi;

/**
 * @author Amir
 */
public interface IDefaultAuditLogCategorizer
        extends IDefaultVaselineLogCategorizer {

    @Override
    default int priority() {
        return 10;
    }

    @Override
    default String prepareLog(final String source, final String category, final VaselineLogLevel logLevel, final
                                String formattedMessage, final Object... args) throws LogException {
        if (category.equals(IImplementedAuditLogApi.AUDIT_LOG_CATEGORY)) {
            // no need for other loggers to log audit messages
            throw new LogInterruptOthersException(IDefaultVaselineLogCategorizer.super.prepareLog(source,
                    category, logLevel, formattedMessage, args));
        }
        return null;
    }
}
