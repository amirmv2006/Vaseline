package ir.amv.os.vaseline.service.basic.api.validation.exc;

import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;

/**
 * Created by AMV on 4/11/2017.
 */
public class ValidationExternalException
        extends BaseExternalException {

    public ValidationExternalException(String messageKey) {
        super(messageKey);
    }
}
