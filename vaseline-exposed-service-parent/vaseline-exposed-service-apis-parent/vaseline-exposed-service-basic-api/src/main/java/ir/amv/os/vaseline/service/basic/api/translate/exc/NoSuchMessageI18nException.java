package ir.amv.os.vaseline.service.basic.api.translate.exc;

import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;

public class NoSuchMessageI18nException extends BaseExternalException {

    /**
     * all client exceptions have one constructor only cause that's what.
     *
     * @param messageKey
     */
    public NoSuchMessageI18nException(String messageKey) {
        super(messageKey);
    }
}
