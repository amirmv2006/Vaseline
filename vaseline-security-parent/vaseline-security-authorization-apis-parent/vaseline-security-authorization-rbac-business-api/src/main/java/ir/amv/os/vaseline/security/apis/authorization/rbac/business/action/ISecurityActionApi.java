package ir.amv.os.vaseline.security.apis.authorization.rbac.business.action;

import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.action.ISecurityAction;

/**
 * @author Amir
 */
public interface ISecurityActionApi<A extends ISecurityAction<A>>
        extends IBaseReadOnlyApi<A, Long> {
}
