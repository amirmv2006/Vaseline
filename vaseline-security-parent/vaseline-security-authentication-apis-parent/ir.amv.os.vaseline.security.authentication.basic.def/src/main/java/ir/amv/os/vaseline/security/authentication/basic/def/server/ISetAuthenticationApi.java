package ir.amv.os.vaseline.security.authentication.basic.def.server;

import ir.amv.os.vaseline.basics.core.api.shared.util.listener.IHasListener;
import ir.amv.os.vaseline.security.authentication.basic.def.listener.ICurrentUserListener;

/**
 * @author Amir
 */
public interface ISetAuthenticationApi extends IHasListener<ICurrentUserListener> {

    void setCurrentUsername(final String username);

}
