package ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.adder.exc;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

/**
 * @author Amir
 */
public class AutoUserAddConfigProblem extends BaseVaselineServerException {
    public AutoUserAddConfigProblem() {
    }

    public AutoUserAddConfigProblem(final String message) {
        super(message);
    }

    public AutoUserAddConfigProblem(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AutoUserAddConfigProblem(final Throwable cause) {
        super(cause);
    }
}
