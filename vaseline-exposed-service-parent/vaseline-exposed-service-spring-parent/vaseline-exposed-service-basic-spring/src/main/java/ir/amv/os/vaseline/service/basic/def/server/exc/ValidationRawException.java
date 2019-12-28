package ir.amv.os.vaseline.service.basic.def.server.exc;

import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
public class ValidationRawException extends BaseBusinessException {

    private final Set<ConstraintViolation<?>> constraintViolations;

    public ValidationRawException(Set<ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return constraintViolations;
    }
}
