package ir.amv.os.vaseline.security.apis.authorization.basic.server.exception;

/**
 * Created by AMV on 2/28/2016.
 */
public class VaselineAccessDeniedException extends VaselineAuthorizationException {
    private final String username;
    private final String actionTreeName;

    public VaselineAccessDeniedException(final String username, final String actionTreeName) {
        this.username = username;
        this.actionTreeName = actionTreeName;
    }

    public String getUsername() {
        return username;
    }

    public String getActionTreeName() {
        return actionTreeName;
    }
}
