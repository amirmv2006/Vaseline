package ir.amv.os.vaseline.basics.validation.api.server.exc;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

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
