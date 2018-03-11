package ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Amir
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoRole {
    String roleName();
    String[] actionTreeNames();
}
