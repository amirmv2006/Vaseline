package ir.amv.os.vaseline.security.authorization.rbac.auto.user.adder.osgi.service;

import ir.amv.os.vaseline.basics.base.osgi.conf.IBaseConfig;

/**
 * @author Amir
 */
public interface IAuthoUserAdder extends IBaseConfig {
    void currentUserChanged(String username);
}
