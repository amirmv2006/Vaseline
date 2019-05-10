package ir.amv.os.vaseline.security.authorization.rbac.business.def.action;

import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.action.ISecurityActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.action.ISecurityAction;

/**
 * @author Amir
 */
public interface IDefaultSecurityActionApi<A extends ISecurityAction<A>, DAO extends ISecurityActionDao<A>>
        extends IDefaultReadOnlyApi<Long, A, DAO>, ISecurityActionApi<A> {

}
