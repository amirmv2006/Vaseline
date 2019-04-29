package ir.amv.os.vaseline.security.authorization.rbac.auto.user.annot.osgi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Amir
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoUserAdd {

    AutoUser[] defaultUsers();
    AutoRole[] defaultRoles();
}