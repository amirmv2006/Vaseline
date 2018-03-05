package ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.action;

import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.action.ISecurityActionDao;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedAuthorizationActionApi
        extends IAuthorizationActionApi {

    ISecurityActionDao<?> getDao();

    default List<String> getActionChildTreeNames(final String baseActionTN) {
        return getDao().getActionChildTreeNames(baseActionTN);
    }
}
