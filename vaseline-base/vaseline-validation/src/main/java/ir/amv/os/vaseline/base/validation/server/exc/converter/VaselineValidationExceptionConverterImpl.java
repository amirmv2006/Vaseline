package ir.amv.os.vaseline.base.validation.server.exc.converter;

import ir.amv.os.vaseline.base.core.server.base.exc.converter.impl.BaseExceptionConverterImpl;
import ir.amv.os.vaseline.base.core.server.base.exc.handler.impl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.base.i18n.server.resolver.VaselineI18nResolver;
import ir.amv.os.vaseline.base.validation.server.exc.VaselineValidationServerException;
import ir.amv.os.vaseline.base.validation.shared.VaselineValidationClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
@Component
public class VaselineValidationExceptionConverterImpl
        extends BaseExceptionConverterImpl<VaselineValidationServerException, VaselineValidationClientException> {
    private VaselineI18nResolver vaselineI18nResolver;

    @Autowired
    public VaselineValidationExceptionConverterImpl(CoreExceptionHandlerImpl exceptionHandler, VaselineI18nResolver vaselineI18nResolver) {
        super(exceptionHandler);
        this.vaselineI18nResolver = vaselineI18nResolver;
    }

    @Override
    public VaselineValidationClientException convertException(VaselineValidationServerException exception) {
        Set<ConstraintViolation<?>> contraintViolations = exception.getContraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> contraintViolation : contraintViolations) {
            Path propertyPath = contraintViolation.getPropertyPath();
            Class<?> rootBeanClass = contraintViolation.getRootBeanClass();
            String translatedProperty = vaselineI18nResolver.translateProperty(rootBeanClass, propertyPath.toString());
            sb.append(translatedProperty + " " + contraintViolation.getMessage());
            sb.append(System.getProperty("line.separator"));
        }
        return new VaselineValidationClientException(sb.toString());
    }
}
