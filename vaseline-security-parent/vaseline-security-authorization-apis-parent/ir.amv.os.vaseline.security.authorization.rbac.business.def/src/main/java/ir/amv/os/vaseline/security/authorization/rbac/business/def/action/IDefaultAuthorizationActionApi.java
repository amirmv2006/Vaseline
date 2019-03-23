package ir.amv.os.vaseline.security.authorization.rbac.business.def.action;

import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.action.ISecurityActionDao;

import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultAuthorizationActionApi
        extends IAuthorizationActionApi {

    ISecurityActionDao<?> getDao();

    default List<String> getActionChildTreeNames(final String baseActionTN) {
        return getDao().getActionChildTreeNames(baseActionTN);
    }
}
