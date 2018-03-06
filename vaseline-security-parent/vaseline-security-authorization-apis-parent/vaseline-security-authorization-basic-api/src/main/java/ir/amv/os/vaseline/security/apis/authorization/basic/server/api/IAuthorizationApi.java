package ir.amv.os.vaseline.security.apis.authorization.basic.server.api;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IAuthorizationApi {

    void checkAuthorization(String actionTreeName) throws VaselineAuthorizationException;

    List<String> getAuthorizedActionTreeNames() throws VaselineAuthorizationException;

}
