package ir.amv.os.vaseline.security.authorization.basic.api.shared;

import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;

/**
 * @author Amir
 */
public class VaselineClientAuthorizationException
        extends BaseVaselineClientException {
    public VaselineClientAuthorizationException(final String messageKey) {
        super(messageKey);
    }
}
