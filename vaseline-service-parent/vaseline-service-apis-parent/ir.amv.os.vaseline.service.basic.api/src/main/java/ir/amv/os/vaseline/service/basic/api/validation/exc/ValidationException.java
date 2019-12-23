package ir.amv.os.vaseline.service.basic.api.validation.exc;

import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
public class ValidationException extends BaseBusinessException {

    private Set<ConstraintViolation<?>> constraintViolations;

    public ValidationException(Set<ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return constraintViolations;
    }
}
