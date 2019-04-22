package ir.amv.os.vaseline.security.authorization.basic.api.server.exception;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

/**
 * @author Amir
 */
public class VaselineAuthorizationException
        extends BaseVaselineServerException {

    public VaselineAuthorizationException() {
    }

    public VaselineAuthorizationException(final Exception e) {
        super(e);
    }
}
