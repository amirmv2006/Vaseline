package ir.amv.os.vaseline.security.audit.basic.api.server;

import ir.amv.os.vaseline.business.basic.api.layer.base.IBaseApi;

import java.util.Map;

/**
 * @author Amir
 */
public interface IAuditApi
        extends IBaseApi {

    void auditBusinessAction(String user, String action, String result, Map<String, Object> metadata, Object... params);
}
