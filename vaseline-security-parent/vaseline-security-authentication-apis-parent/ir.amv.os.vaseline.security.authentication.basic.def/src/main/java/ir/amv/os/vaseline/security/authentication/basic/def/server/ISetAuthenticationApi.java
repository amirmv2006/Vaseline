package ir.amv.os.vaseline.security.authentication.basic.def.server;

import ir.amv.os.vaseline.basics.core.api.utils.ds.IListenable;
import ir.amv.os.vaseline.security.authentication.basic.def.listener.ICurrentUserListener;

/**
 * @author Amir
 */
public interface ISetAuthenticationApi extends IListenable<ICurrentUserListener> {

    void setCurrentUsername(final String username);

}
