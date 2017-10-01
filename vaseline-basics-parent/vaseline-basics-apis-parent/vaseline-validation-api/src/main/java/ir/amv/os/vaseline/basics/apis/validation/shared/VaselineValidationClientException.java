package ir.amv.os.vaseline.basics.apis.validation.shared;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationClientException
        extends BaseVaselineClientException {

    public VaselineValidationClientException(String messageKey) {
        super(messageKey);
    }
}
