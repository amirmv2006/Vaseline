package ir.amv.os.vaseline.security.apis.authorization.basic.server.api;

import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;

import java.util.List;

/**
 * @author Amir
 */
public interface IAuthorizationActionApi extends IBaseApi {
    List<String> getActionChildTreeNames(final String baseActionTN);
}
