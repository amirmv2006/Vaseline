package ir.amv.os.vaseline.security.apis.authorization.basic.shared;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;

/**
 * @author Amir
 */
public class VaselineClientAuthorizationException
        extends BaseVaselineClientException {
    public VaselineClientAuthorizationException(final String messageKey) {
        super(messageKey);
    }
}
