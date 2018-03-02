package ir.amv.os.vaseline.security.apis.auditimpl.log.server.categorizer;

import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogException;
import ir.amv.os.vaseline.basics.apis.logging.server.exc.LogInterruptOthersException;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.apis.loggingimpl.server.categorizer.IImplementedVaselineLogCategorizer;
import ir.amv.os.vaseline.security.apis.auditimpl.log.server.basic.IImplementedAuditLogApi;

/**
 * @author Amir
 */
public interface IImplementedAuditLogCategorizer
        extends IImplementedVaselineLogCategorizer {

    @Override
    default int prioirity() {
        return 10;
    }

    @Override
    default String prepareLog(final String source, final String category, final VaselineLogLevel logLevel, final
                                String formattedMessage, final Object... args) throws LogException {
        if (category.equals(IImplementedAuditLogApi.AUDIT_LOG_CATEGORY)) {
            // no need for other loggers to log audit messages
            throw new LogInterruptOthersException(IImplementedVaselineLogCategorizer.super.prepareLog(source,
                    category, logLevel, formattedMessage, args));
        }
        return null;
    }
}
