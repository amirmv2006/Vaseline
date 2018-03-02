package ir.amv.os.vaseline.security.apis.audit.basic.server;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;

import java.util.Map;

/**
 * @author Amir
 */
public interface IAuditApi
        extends IBaseApi {

    void auditBusinessAction(String user, String action, String result, Map<String, Object> metadata, Object... params);
}
