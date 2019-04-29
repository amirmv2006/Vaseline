package ir.amv.os.vaseline.basics.validation.api.server.exc.converter;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.ExceptionConversionException;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def.IDefaultExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.validation.api.server.exc.VaselineValidationServerException;
import ir.amv.os.vaseline.basics.validation.api.shared.VaselineValidationClientException;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationExceptionConverterImpl
        implements IDefaultExceptionConverter<VaselineValidationServerException, VaselineValidationClientException> {

    private IVaselineMessageTranslator messageTranslator;

    @Override
    public IVaselineMessageTranslator getMessageTranslator() {
        return messageTranslator;
    }

    @Override
    public Class<VaselineValidationServerException> getExceptionClass() {
        return VaselineValidationServerException.class;
    }

    @Override
    public Class<VaselineValidationClientException> getClientExceptionClass() {
        return VaselineValidationClientException.class;
    }

    @Override
    public void setExceptionHandler(final ICoreExceptionHandler exceptionHandler) {
        injectExceptionHandler(exceptionHandler);
    }

    @Override
    public VaselineValidationClientException convertException(VaselineValidationServerException exception) throws ExceptionConversionException {
        Set<ConstraintViolation<?>> contraintViolations = exception.getContraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> contraintViolation : contraintViolations) {
            Path propertyPath = contraintViolation.getPropertyPath();
            Class<?> rootBeanClass = contraintViolation.getRootBeanClass();
            String translatedProperty = getMessageTranslator().translateProperty(rootBeanClass, propertyPath.toString());
            sb.append(translatedProperty).append(" ").append(contraintViolation.getMessage());
            sb.append(System.getProperty("line.separator"));
        }
        return new VaselineValidationClientException(sb.toString());
    }


    public void setMessageTranslator(IVaselineMessageTranslator messageTranslator) {
        this.messageTranslator = messageTranslator;
    }
}