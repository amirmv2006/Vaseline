package ir.amv.os.vaseline.security.authorization.rbac.auto.user.adder.osgi.exc;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

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
