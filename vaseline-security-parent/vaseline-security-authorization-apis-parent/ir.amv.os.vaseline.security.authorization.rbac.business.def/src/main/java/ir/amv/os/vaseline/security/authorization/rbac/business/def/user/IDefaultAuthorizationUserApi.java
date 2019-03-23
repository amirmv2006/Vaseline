package ir.amv.os.vaseline.security.authorization.rbac.business.def.user;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.user.ISecurityUserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultAuthorizationUserApi
        extends IAuthorizationUserApi {

    ISecurityUserDao<?> getDao();

    @Override
    default List<String> getUsernamesWithAccessToActionTreeName(final String actionTreeName) throws BaseVaselineServerException {
        List<String> actionTreeNames = new ArrayList<>();
        String counter = actionTreeName;
        while (counter.contains(IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER)) {
            actionTreeNames.add(counter);
            counter = counter.substring(0, counter.lastIndexOf(IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER));
        }
        actionTreeNames.add(counter);
        return getDao().getUsernamesWithAccessToActionTreeName(actionTreeNames.toArray(new String[0]));
    }

    @Override
    default List<String> getUserActionTreeNames(String currentUsername) throws BaseVaselineServerException {
        return getDao().getUserActionTreeNames(currentUsername);
    }

    @Override
    default boolean hasUserAccessToAction(String username, String actionTreeName) throws BaseVaselineServerException {
        List<String> userActionTreeNames = getDao().getUserActionTreeNames(username);
        for (String userActionTreeName : userActionTreeNames) {
            if (actionTreeName.equals(userActionTreeName) ||
                    actionTreeName.startsWith(userActionTreeName + IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER)) {
                return true;
            }
        }
        return false;
    }
}
