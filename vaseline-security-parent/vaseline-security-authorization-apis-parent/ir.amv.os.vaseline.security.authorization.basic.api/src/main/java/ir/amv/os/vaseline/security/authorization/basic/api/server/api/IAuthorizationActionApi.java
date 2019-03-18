package ir.amv.os.vaseline.security.authorization.basic.api.server.api;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationActionApi {
    String ACTION_TREE_NAME_SPLITTER = ":";

    List<String> getActionChildTreeNames(final String baseActionTN);
}
