package ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.annot;

import ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.adder.service.defimpl.AutoUserAdder;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Amir
 */
public class AutoUserAddParser extends ServiceTracker {
    private ServiceTracker<ConfigurationAdmin, ConfigurationAdmin> confAdminTracker;

    public AutoUserAddParser(final BundleContext context) throws InvalidSyntaxException {
        super(context, context.createFilter("(objectClass=*)"), null);
        confAdminTracker = new ServiceTracker<ConfigurationAdmin, ConfigurationAdmin>(context, ConfigurationAdmin
                .class, null);
    }

    @Override
    public void open() {
        super.open();
        confAdminTracker.open();
    }

    @Override
    public void close() {
        super.close();
        confAdminTracker.close();
    }

    @Override
    public Object addingService(final ServiceReference reference) {
        Object objectClass = reference.getProperty("objectClass");
        String[] serviceClasses = (String[]) objectClass;
        boolean found = false;
        for (String serviceClass : serviceClasses) {
            if (serviceClass.contains("AuthorRestService")) {
                found = true;
            }
        }
        if (!found) {
            return null;
        }
        Object service = super.addingService(reference);
        if (service != null) {
            AutoUserAdd autoUserAdd = ReflectionUtil.getAnnotationInHierarchy(service.getClass(), AutoUserAdd.class);
            if (autoUserAdd != null) {
                try {
                    handle(autoUserAdd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return service;
    }

    private void handle(final AutoUserAdd autoUserAdd) throws IOException {
        Configuration configuration = confAdminTracker.getService().getConfiguration(AutoUserAdder.PID, null);
        Dictionary<String, Object> root = configuration.getProperties() == null ? new Hashtable<>() : configuration.getProperties();
        addDefaultRoles(autoUserAdd, root);
        addDefaultUsers(autoUserAdd, root);
        configuration.update(root);
    }

    private void addDefaultRoles(final AutoUserAdd autoUserAdd, final Dictionary<String, Object> root) {
        AutoRole[] defaultRoles = autoUserAdd.defaultRoles();
        String roleKey = AutoUserAdder.DEFAULT_ROLE_KEY_PREFIX;
        int i = 0;
        for (AutoRole defaultRole : defaultRoles) {
            String roleName = defaultRole.roleName();
            root.put(roleKey, roleName);
            String[] actionTreeNames = defaultRole.actionTreeNames();
            String actionKey = roleKey + AutoUserAdder.DEFAULT_ROLE_ACTIONS_KEY_POSTFIX;
            int j = 0;
            for (String actionTreeName : actionTreeNames) {
                root.put(actionKey, actionTreeName);
                j++;
                actionKey = roleKey + AutoUserAdder.DEFAULT_ROLE_ACTIONS_KEY_POSTFIX + "." + j;
            }
            i++;
            roleKey = AutoUserAdder.DEFAULT_ROLE_KEY_PREFIX + "." + i;
        }
    }

    private void addDefaultUsers(final AutoUserAdd autoUserAdd, final Dictionary<String, Object> root) {
        AutoUser[] defaultUsers = autoUserAdd.defaultUsers();
        String userKey = AutoUserAdder.DEFAULT_USER_KEY_PREFIX;
        int i = 0;
        for (AutoUser defaultUser : defaultUsers) {
            String username = defaultUser.username();
            root.put(userKey, username);
            String[] roleNames = defaultUser.roleNames();
            String roleKey = userKey + AutoUserAdder.DEFAULT_USER_ROLE_POSTFIX;
            int j = 0;
            for (String roleName : roleNames) {
                root.put(roleKey, roleName);
                j++;
                roleKey = userKey + AutoUserAdder.DEFAULT_USER_ROLE_POSTFIX + "." + j;
            }
            i++;
            userKey = AutoUserAdder.DEFAULT_USER_KEY_PREFIX + "." + i;
        }
    }

    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        if (service != null) {
            super.removedService(reference, service);
        }
    }

}
