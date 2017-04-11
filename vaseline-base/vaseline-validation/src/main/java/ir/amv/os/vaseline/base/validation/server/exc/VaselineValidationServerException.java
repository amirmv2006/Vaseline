package ir.amv.os.vaseline.base.validation.server.exc;

import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationServerException extends BaseVaselineServerException {

    private Set<ConstraintViolation<?>> contraintViolations;

    public VaselineValidationServerException(Set<ConstraintViolation<?>> contraintViolations) {
        this.contraintViolations = contraintViolations;
    }

    public Set<ConstraintViolation<?>> getContraintViolations() {
        return contraintViolations;
    }
}
