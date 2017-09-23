package ir.amv.os.vaseline.security.authentication.api.shared.api;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IAuthenticationApi extends IBaseApi {

    String getCurrentUsername() throws BaseVaselineServerException;
}
