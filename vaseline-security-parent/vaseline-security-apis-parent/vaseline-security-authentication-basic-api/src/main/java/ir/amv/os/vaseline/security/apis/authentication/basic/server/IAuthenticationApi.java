package ir.amv.os.vaseline.security.apis.authentication.basic.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IAuthenticationApi extends IBaseApi {

    String getCurrentUsername() throws BaseVaselineServerException;
}
