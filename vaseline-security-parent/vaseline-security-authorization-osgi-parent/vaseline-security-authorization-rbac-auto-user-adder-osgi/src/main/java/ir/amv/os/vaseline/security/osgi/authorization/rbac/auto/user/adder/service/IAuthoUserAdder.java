package ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.adder.service;

import ir.amv.os.vaseline.basics.osgi.base.conf.IBaseConfig;

/**
 * @author Amir
 */
public interface IAuthoUserAdder extends IBaseConfig {
    void currentUserChanged(String username);
}
