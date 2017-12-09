package ir.amv.os.vaseline.security.apis.authorization.basic.server;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAuthorizationException;

import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IAuthorizationApi extends IBaseApi {

    void checkAuthorization(String operationTreeName) throws VaselineAuthorizationException;

    List<String> getAuthorizedOperationTreeNames() throws VaselineAuthorizationException;

    List<String> getChildTreeNamesOfAuthorizedOperation(String baseOperationTN) throws VaselineAuthorizationException;

    List<String> getUsernamesWithPermissionToOperation(String operationTreeName) throws VaselineAuthorizationException;

}
