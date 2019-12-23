package ir.amv.os.vaseline.service.basic.api.validation.exc.converter;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.ExceptionConversionException;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def.IDefaultExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.service.basic.api.validation.exc.ValidationException;
import ir.amv.os.vaseline.basics.validation.api.shared.ValidationExternalException;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationExceptionConverterImpl
        implements IDefaultExceptionConverter<ValidationException, ValidationExternalException> {

    private IVaselineMessageTranslator messageTranslator;

    @Override
    public IVaselineMessageTranslator getMessageTranslator() {
        return messageTranslator;
    }

    @Override
    public Class<ValidationException> getExceptionClass() {
        return ValidationException.class;
    }

    @Override
    public Class<ValidationExternalException> getClientExceptionClass() {
        return ValidationExternalException.class;
    }

    @Override
    public void setExceptionHandler(final ICoreExceptionHandler exceptionHandler) {
        injectExceptionHandler(exceptionHandler);
    }

    @Override
    public ValidationExternalException convertException(ValidationException exception) throws ExceptionConversionException {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            Path propertyPath = constraintViolation.getPropertyPath();
            Class<?> rootBeanClass = constraintViolation.getRootBeanClass();
            String translatedProperty = getMessageTranslator().translateProperty(rootBeanClass, propertyPath.toString());
            sb.append(translatedProperty).append(" ").append(constraintViolation.getMessage());
            sb.append(System.getProperty("line.separator"));
        }
        return new ValidationExternalException(sb.toString());
    }


    public void setMessageTranslator(IVaselineMessageTranslator messageTranslator) {
        this.messageTranslator = messageTranslator;
    }
}
