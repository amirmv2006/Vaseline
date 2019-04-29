package ir.amv.os.vaseline.security.authorization.rbac.auto.user.adder.osgi.service.defimpl;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.common.osgi.server.helper.LOGGER;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.role.SecurityRoleEntity;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.user.SecurityUserEntity;
import ir.amv.os.vaseline.security.authorization.rbac.auto.user.adder.osgi.exc.AutoUserAddConfigProblem;
import ir.amv.os.vaseline.security.authorization.rbac.auto.user.adder.osgi.service.IAuthoUserAdder;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.action.IVaselineSecurityActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.role.IVaselineSecurityRoleApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.user.IVaselineSecurityUserApi;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        property = Constants.SERVICE_PID + "=ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.adder",
        service = {
                ManagedService.class,
                IAuthoUserAdder.class
        }
)
public class AutoUserAdder implements ManagedService, IAuthoUserAdder {
    public static final String PID = "ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.adder";
    public static final String DEFAULT_USER_KEY_PREFIX = PID + ".user.default";
    public static final String DEFAULT_USER_ROLE_POSTFIX = PID + ".role";
    public static final String DEFAULT_ROLE_KEY_PREFIX = PID + ".role.default";
    public static final String DEFAULT_ROLE_ACTIONS_KEY_POSTFIX = ".action";
    public static final String DEFAULT_ROLE_ACTIONS_KEY_PREFIX = DEFAULT_ROLE_KEY_PREFIX + DEFAULT_ROLE_ACTIONS_KEY_POSTFIX;

    private Set<SecurityRoleEntity> defaultRoles = new HashSet<>();

    private IVaselineSecurityUserApi vaselineSecurityUserApi;
    private IVaselineSecurityRoleApi vaselineSecurityRoleApi;
    private IVaselineSecurityActionApi vaselineSecurityActionApi;

    @Override
    public void updated(final Dictionary<String, ?> properties) throws ConfigurationException {
        if (properties == null) {
            return;
        }
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    doUpdate(properties);
                } catch (ConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }, 5000);
    }

    private synchronized void doUpdate(final Dictionary<String, ?> properties) throws ConfigurationException {
        try {
            Set<SecurityRoleEntity> roles = extractDefaultRoles(DEFAULT_ROLE_KEY_PREFIX, properties);
            defaultRoles.addAll(roles);
        } catch (BaseVaselineServerException e) {
            throw new ConfigurationException(DEFAULT_ROLE_KEY_PREFIX, e.getLocalizedMessage(), e);
        }
        try {
            extractDefaultUsers(properties);
        } catch (BaseVaselineServerException e) {
            throw new ConfigurationException(DEFAULT_USER_KEY_PREFIX, e.getLocalizedMessage(), e);
        }
    }

    private void extractDefaultUsers(final Dictionary<String, ?> properties) throws BaseVaselineServerException {
        String userBaseKey = DEFAULT_USER_KEY_PREFIX;
        String defaultUserName = getConfig(userBaseKey, properties, null);
        int i = 0;
        if (defaultUserName == null) {
            userBaseKey = DEFAULT_USER_KEY_PREFIX + "." + i;
            defaultUserName = getConfig(userBaseKey, properties, null);
        }
        while (defaultUserName != null) {
            SecurityUserEntity defaultUser = getUserApi().loadUserByUsername(defaultUserName);
            if (defaultUser == null) {
                defaultUser = new SecurityUserEntity();
                defaultUser.setUsername(defaultUserName);
                Set<SecurityRoleEntity> roles = extractDefaultRoles(userBaseKey + DEFAULT_USER_ROLE_POSTFIX, properties);
                roles.addAll(defaultRoles);
                defaultUser.setRoles(roles);
                getUserApi().saveNotSecured(defaultUser);
            }
            i++;
            userBaseKey = DEFAULT_USER_KEY_PREFIX + "." + i;
            defaultUserName = getConfig(userBaseKey, properties, null);
        }
    }

    private Set<SecurityRoleEntity> extractDefaultRoles(
            final String roleBaseKey,
            final Dictionary<String, ?> properties) throws BaseVaselineServerException {
        Set<SecurityRoleEntity> roles = new HashSet<>();
        String roleBaseKeyCnt = roleBaseKey;
        String defaultRoleName = getConfig(roleBaseKeyCnt, properties, null);
        int i = 0;
        if (defaultRoleName == null) {
            roleBaseKeyCnt = DEFAULT_ROLE_KEY_PREFIX + "." + i;
            defaultRoleName = getConfig(roleBaseKeyCnt, properties, null);
        }
        while (defaultRoleName != null) {
            SecurityRoleEntity defaultRole = getRoleApi().getRoleByName(defaultRoleName);
            if (defaultRole == null) {
                defaultRole = new SecurityRoleEntity();
                defaultRole.setRoleName(defaultRoleName);
                defaultRole.setActions(new HashSet<>());
                extractDefaultRoleActions(roleBaseKeyCnt + DEFAULT_ROLE_ACTIONS_KEY_POSTFIX, defaultRole, properties);
                getRoleApi().saveNotSecured(defaultRole);
            }
            roles.add(defaultRole);
            i++;
            roleBaseKeyCnt = DEFAULT_ROLE_KEY_PREFIX + "." + i;
            defaultRoleName = getConfig(roleBaseKeyCnt, properties, null);
        }
        return roles;
    }

    private void extractDefaultRoleActions(
            final String actionBaseKey,
            final SecurityRoleEntity defaultRole,
            final Dictionary<String, ?> properties) throws BaseVaselineServerException {
        String actionBaseKeyCnt = actionBaseKey;
        String actionTreeName = getConfig(actionBaseKeyCnt, properties, null);
        int i = 0;
        if (actionTreeName == null) {
            actionBaseKeyCnt = actionBaseKey + "." + i;
            actionTreeName = getConfig(actionBaseKeyCnt, properties, null);
        }
        while (actionTreeName != null) {
            SecurityActionEntity action = getActionApi().getActionByTreeName(actionTreeName);
            if (action == null) {
                action = new SecurityActionEntity();
                action.setActionTreeName(actionTreeName);
                if (actionTreeName.contains(IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER)) {
                    String parentActionTreeName = actionTreeName.substring(0, actionTreeName.lastIndexOf
                            (IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER));
                    SecurityActionEntity parent = getActionApi().getActionByTreeName(parentActionTreeName);
                    if (parent == null) {
                        throw new AutoUserAddConfigProblem(String.format(
                                "Parent Action %s doesn't exist, remember to add parents first!", parentActionTreeName
                        ));
                    }
                    action.setParent(parent);
                }
                getActionApi().saveNotSecured(action);
                defaultRole.getActions().add(action);
            }
            i++;
            actionBaseKeyCnt = actionBaseKey + "." + i;
            actionTreeName = getConfig(actionBaseKeyCnt, properties, null);
        }
    }

    private IVaselineSecurityRoleApi getRoleApi() {
        return vaselineSecurityRoleApi.getProxy(IVaselineSecurityRoleApi.class);
    }

    private IVaselineSecurityActionApi getActionApi() {
        return vaselineSecurityActionApi.getProxy(IVaselineSecurityActionApi.class);
    }

    private IVaselineSecurityUserApi getUserApi() {
        return vaselineSecurityUserApi.getProxy(IVaselineSecurityUserApi.class);
    }

    @Override
    public void currentUserChanged(final String username) {
        try {
            SecurityUserEntity user = getUserApi().loadUserByUsername(username);
            if (user == null) {
                user = new SecurityUserEntity();
                user.setUsername(username);
                user.setRoles(defaultRoles);
                getUserApi().saveNotSecured(user);
            }
        } catch (BaseVaselineServerException ignored) {
            LOGGER.log(VaselineLogLevel.ERROR, "Problem adding user while adding %s", username);
        }
    }

    @Reference
    public void setVaselineSecurityUserApi(final IVaselineSecurityUserApi vaselineSecurityUserApi) {
        this.vaselineSecurityUserApi = vaselineSecurityUserApi;
    }

    @Reference
    public void setVaselineSecurityActionApi(final IVaselineSecurityActionApi vaselineSecurityActionApi) {
        this.vaselineSecurityActionApi = vaselineSecurityActionApi;
    }

    @Reference
    public void setVaselineSecurityRoleApi(final IVaselineSecurityRoleApi vaselineSecurityRoleApi) {
        this.vaselineSecurityRoleApi = vaselineSecurityRoleApi;
    }

}