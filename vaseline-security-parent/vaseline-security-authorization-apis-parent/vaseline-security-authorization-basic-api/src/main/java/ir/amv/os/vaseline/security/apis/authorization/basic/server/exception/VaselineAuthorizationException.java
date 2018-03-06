package ir.amv.os.vaseline.security.apis.authorization.basic.server.exception;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

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
