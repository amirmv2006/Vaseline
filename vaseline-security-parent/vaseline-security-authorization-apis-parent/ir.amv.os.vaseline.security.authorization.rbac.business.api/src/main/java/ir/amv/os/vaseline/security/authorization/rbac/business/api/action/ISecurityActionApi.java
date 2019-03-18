package ir.amv.os.vaseline.security.authorization.rbac.business.api.action;

import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.action.ISecurityAction;

/**
 * @author Amir
 */
public interface ISecurityActionApi<A extends ISecurityAction<A>>
        extends IBaseReadOnlyApi<A, Long> {
}
