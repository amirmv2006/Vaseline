package ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.user;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authentication.businessimpl.server.base.IImplementedBaseUserApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.business.user.ISecurityUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user.ISecurityUserWithRoleEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedSecurityUserApi<U extends ISecurityUserWithRoleEntity<?>, Dao extends ISecurityUserDao<U>>
        extends IBaseImplementedReadOnlyApi<U, Long, Dao>, IImplementedBaseUserApi<U, Dao>, ISecurityUserApi<U> {

    @Override
    @Transactional
    @VaselineBuinessMetadata(
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    )
    default List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<String>>)() -> {
            List<String> actionTreeNames = new ArrayList<>();
            String counter = actionTreeName;
            while (counter.contains(IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER)) {
                actionTreeNames.add(counter);
                counter = counter.substring(0, counter.lastIndexOf(IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER));
            }
            actionTreeNames.add(counter);
            return getDao().getUsernamesWithAccessToActionTreeName(actionTreeNames.toArray(new String[0]));
        });
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata(
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    )
    default List<String> getUserActionTreeNames(String currentUsername) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<List<String>>)() -> getDao().getUserActionTreeNames(currentUsername));
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata(
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    )
    default boolean hasUserAccessToAction(String username, String actionTreeName) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<Boolean>)() -> {
            List<String> userActionTreeNames = getDao().getUserActionTreeNames(username);
            for (String userActionTreeName : userActionTreeNames) {
                if (actionTreeName.equals(userActionTreeName) ||
                        actionTreeName.startsWith(userActionTreeName + IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER)) {
                    return true;
                }
            }
            return false;
        });
    }
}
