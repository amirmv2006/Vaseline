package ir.amv.os.vaseline.security.authorization.rbac.dao.api.action;

import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.action.ISecurityAction;

import java.util.List;

/**
 * @author Amir
 */
public interface ISecurityActionDao<A extends ISecurityAction<A>>
        extends IBaseReadOnlyDao<A, Long> {

    List<String> getActionChildTreeNames(final String baseActionTN);
}
