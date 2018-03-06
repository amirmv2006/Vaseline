package ir.amv.os.vaseline.security.apis.authorization.basic.server.api;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationActionApi {
    String ACTION_TREE_NAME_SPLITTER = ":";

    List<String> getActionChildTreeNames(final String baseActionTN);
}
