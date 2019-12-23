package ir.amv.os.vaseline.security.authorization.basic.api.server.exception;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;

/**
 * @author Amir
 */
public class VaselineAuthorizationException
        extends BaseBusinessException {

    public VaselineAuthorizationException() {
    }

    public VaselineAuthorizationException(final Exception e) {
        super(e);
    }
}
