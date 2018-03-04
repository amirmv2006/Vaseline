package ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.action;

import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.business.action.ISecurityActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.action.ISecurityAction;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedSecurityActionApi<A extends ISecurityAction<A>, DAO extends ISecurityActionDao<A>>
        extends IBaseImplementedReadOnlyApi<A, Long, DAO>, ISecurityActionApi<A> {

    @Override
    default List<String> getActionChildTreeNames(final String baseActionTN) {
        return getDao().getActionChildTreeNames(baseActionTN);
    }
}
