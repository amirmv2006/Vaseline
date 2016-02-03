package ir.amv.os.vaseline.base.mapper.server.exc;

import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import org.dozer.MappingException;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by AMV on 1/16/2016.
 */
public class VaselineConvertException extends BaseVaselineServerException {

    private Set<ConstraintViolation<?>> contraintViolations;
    private MappingException mappingException;

    public VaselineConvertException(Set<ConstraintViolation<?>> contraintViolations) {
        this.contraintViolations = contraintViolations;
    }

    public VaselineConvertException(MappingException mappingException) {
        this.mappingException = mappingException;
    }

    public Set<ConstraintViolation<?>> getContraintViolations() {
        return contraintViolations;
    }

    public MappingException getMappingException() {
        return mappingException;
    }
}
