package ir.amv.os.vaseline.security.authorization.basic.api.shared;

/**
 * @author Amir
 */
public class ExternalAccessDeniedException
        extends ExternalAuthorizationException {
    private String username;
    private String actionTreeName;

    public ExternalAccessDeniedException(final String messageKey) {
        super(messageKey);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getActionTreeName() {
        return actionTreeName;
    }

    public void setActionTreeName(final String actionTreeName) {
        this.actionTreeName = actionTreeName;
    }
}
