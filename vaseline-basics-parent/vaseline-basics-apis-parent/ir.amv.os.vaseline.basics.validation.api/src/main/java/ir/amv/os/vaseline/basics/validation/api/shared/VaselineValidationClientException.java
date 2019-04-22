package ir.amv.os.vaseline.basics.validation.api.shared;

import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationClientException
        extends BaseVaselineClientException {

    public VaselineValidationClientException(String messageKey) {
        super(messageKey);
    }
}
