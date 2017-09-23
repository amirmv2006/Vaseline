package ir.amv.os.vaseline.base.validation.shared;

import ir.amv.os.vaseline.base.core.api.shared.base.exc.BaseVaselineClientException;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationClientException
        extends BaseVaselineClientException {

    public VaselineValidationClientException(String messageKey) {
        super(messageKey);
    }
}
