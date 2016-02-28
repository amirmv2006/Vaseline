package ir.amv.os.vaseline.security.authorization.api.shared.api;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.security.authorization.api.shared.criteria.ISecurityCriteria;

import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface INoAuthAuthorizationApi extends IBaseApi {

    void checkAuthorization(String username, String operationTreeName, Object securedObject) throws BaseVaselineServerException;

    void checkAuthorization(String username, String operationTreeName) throws BaseVaselineServerException;

    ISecurityCriteria getSecurityCriteria(String username, String operationTreeName) throws BaseVaselineServerException;

    List<String> getAuthorizedOperationTreeNames(String currentUsername) throws BaseVaselineServerException;

    List<String> getChildTreeNamesOfAuthorizedOperation(String baseOperationTN) throws BaseVaselineServerException;

    List<String> getUsernamesWithPermissionToOperation(String operationTreeName) throws BaseVaselineServerException;

    List<String> getUsernamesWithPermissionToOperationTarget(
            String operationTreeName, Object target) throws BaseVaselineServerException;

}
