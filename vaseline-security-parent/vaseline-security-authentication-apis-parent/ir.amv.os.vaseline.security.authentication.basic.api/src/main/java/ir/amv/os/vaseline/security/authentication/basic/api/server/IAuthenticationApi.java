package ir.amv.os.vaseline.security.authentication.basic.api.server;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IAuthenticationApi {

    String IS_AUTHENTICATED = "IS_AUTHENTICATED";

    String getCurrentUsername() throws BaseVaselineServerException;
}
