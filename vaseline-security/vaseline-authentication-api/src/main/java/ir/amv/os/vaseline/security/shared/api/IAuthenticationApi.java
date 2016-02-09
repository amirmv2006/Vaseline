package ir.amv.os.vaseline.security.shared.api;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IAuthenticationApi extends IBaseApi {

    String getCurrentUsername();
}
