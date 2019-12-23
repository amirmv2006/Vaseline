package ir.amv.os.vaseline.security.authorization.basic.api.shared;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;

/**
 * @author Amir
 */
public class ExternalAuthorizationException
        extends BaseExternalException {
    public ExternalAuthorizationException(final String messageKey) {
        super(messageKey);
    }
}
