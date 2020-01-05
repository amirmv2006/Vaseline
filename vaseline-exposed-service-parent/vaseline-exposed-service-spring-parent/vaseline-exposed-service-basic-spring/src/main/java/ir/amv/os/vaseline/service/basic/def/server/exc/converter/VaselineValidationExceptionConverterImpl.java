package ir.amv.os.vaseline.service.basic.def.server.exc.converter;

import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl.NeedsBeansGenericConverter;
import ir.amv.os.vaseline.service.basic.api.translate.IVaselineMessageTranslator;
import ir.amv.os.vaseline.service.basic.api.validation.exc.ValidationExternalException;
import ir.amv.os.vaseline.service.basic.def.server.exc.ValidationRawException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.TypeDescriptor;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Collections;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationExceptionConverterImpl
        extends NeedsBeansGenericConverter {

    protected VaselineValidationExceptionConverterImpl(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(ValidationRawException.class, ValidationExternalException.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source instanceof ValidationRawException) {
            ValidationRawException validationRawException = (ValidationRawException) source;
            Set<ConstraintViolation<?>> constraintViolations = validationRawException.getConstraintViolations();
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
        return null;
    }

    public IVaselineMessageTranslator getMessageTranslator() {
        return getBean(IVaselineMessageTranslator.class);
    }

}
