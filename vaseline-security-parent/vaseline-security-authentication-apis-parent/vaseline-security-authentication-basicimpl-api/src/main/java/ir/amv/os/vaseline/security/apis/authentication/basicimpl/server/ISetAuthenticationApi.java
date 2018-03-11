package ir.amv.os.vaseline.security.apis.authentication.basicimpl.server;

import ir.amv.os.vaseline.basics.apis.core.shared.util.listener.IHasListener;
import ir.amv.os.vaseline.security.apis.authentication.basicimpl.listener.ICurrentUserListener;

/**
 * @author Amir
 */
public interface ISetAuthenticationApi extends IHasListener<ICurrentUserListener> {

    void setCurrentUsername(final String username);

}
