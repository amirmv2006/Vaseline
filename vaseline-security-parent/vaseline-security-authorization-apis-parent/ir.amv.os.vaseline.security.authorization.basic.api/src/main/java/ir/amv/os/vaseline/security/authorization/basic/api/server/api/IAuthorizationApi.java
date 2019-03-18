package ir.amv.os.vaseline.security.authorization.basic.api.server.api;

import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IAuthorizationApi {

    void checkAuthorization(String actionTreeName) throws VaselineAuthorizationException;

    List<String> getAuthorizedActionTreeNames() throws VaselineAuthorizationException;

}
