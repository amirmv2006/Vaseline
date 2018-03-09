package ir.amv.os.vaseline.security.apis.authentication.basicimpl.listener;

/**
 * @author Amir
 */
public interface ICurrentUserListener {

    void currentUserChanged(final String username);
}
