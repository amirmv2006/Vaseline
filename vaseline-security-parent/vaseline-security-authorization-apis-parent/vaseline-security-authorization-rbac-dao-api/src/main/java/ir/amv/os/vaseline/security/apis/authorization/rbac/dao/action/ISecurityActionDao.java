package ir.amv.os.vaseline.security.apis.authorization.rbac.dao.action;

import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.action.ISecurityAction;

import java.util.List;

/**
 * @author Amir
 */
public interface ISecurityActionDao<A extends ISecurityAction<A>>
        extends IBaseReadOnlyDao<A, Long> {

    List<String> getActionChildTreeNames(final String baseActionTN);
}
