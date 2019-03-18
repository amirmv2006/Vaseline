package ir.amv.os.vaseline.security.audit.def.log.server.basic;

import ir.amv.os.vaseline.basics.logging.api.server.exc.LogException;
import ir.amv.os.vaseline.basics.logging.api.server.logger.IVaselineLogger;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.business.basic.def.server.base.IBaseImplementedApi;
import ir.amv.os.vaseline.security.audit.basic.api.server.IAuditApi;

import java.util.Map;

/**
 * @author Amir
 */
public interface IImplementedAuditLogApi
        extends IBaseImplementedApi, IAuditApi {

    String AUDIT_LOG_CATEGORY = IAuditApi.class.getName();

    IVaselineLogger getVaselineLogger();
    @Override
    default void auditBusinessAction(String user, String action, String result, Map<String, Object> metadata, Object...
            params) { // not a business action
        IVaselineLogger vaselineLogger = getVaselineLogger();

        StringBuilder logMessage = new StringBuilder("Business Action");
        logMessage.append("User (");
        logMessage.append(user);
        logMessage.append(") performed /");
        logMessage.append(action);
        logMessage.append("\\ with result <");
        logMessage.append(result);
        logMessage.append(">");
        if (params != null) {
            logMessage.append(" params: [");
            logMessage.append(vaselineLogger.toLogString(params));
            logMessage.append("]");
        }
        if (metadata != null) {
            logMessage.append(" metadata: ");
            logMessage.append(vaselineLogger.toLogString(metadata));
        }

        try {
            vaselineLogger.log(IAuditApi.class.getName(), AUDIT_LOG_CATEGORY, getBusinessLogLevel(action), logMessage
                    .toString());
        } catch (LogException e) {
            e.printStackTrace();
        }
    }

    default VaselineLogLevel getBusinessLogLevel(String action) {
        return VaselineLogLevel.INFO;
    }
}
