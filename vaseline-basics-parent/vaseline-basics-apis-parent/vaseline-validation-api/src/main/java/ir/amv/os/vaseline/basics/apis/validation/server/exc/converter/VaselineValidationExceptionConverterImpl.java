package ir.amv.os.vaseline.basics.apis.validation.server.exc.converter;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.defimpl.BaseExceptionConverterImpl;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.defimpl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.apis.validation.server.exc.VaselineValidationServerException;
import ir.amv.os.vaseline.basics.apis.validation.shared.VaselineValidationClientException;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Set;

/**
 * Created by AMV on 4/11/2017.
 */
public class VaselineValidationExceptionConverterImpl
        extends BaseExceptionConverterImpl<VaselineValidationServerException, VaselineValidationClientException> {

    public VaselineValidationExceptionConverterImpl(CoreExceptionHandlerImpl exceptionHandler) {
        super(exceptionHandler);
    }

    @Override
    public VaselineValidationClientException convertException(VaselineValidationServerException exception) {
        Set<ConstraintViolation<?>> contraintViolations = exception.getContraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> contraintViolation : contraintViolations) {
            Path propertyPath = contraintViolation.getPropertyPath();
            Class<?> rootBeanClass = contraintViolation.getRootBeanClass();
            String translatedProperty = messageTranslator.translateProperty(rootBeanClass, propertyPath.toString());
            sb.append(translatedProperty + " " + contraintViolation.getMessage());
            sb.append(System.getProperty("line.separator"));
        }
        return new VaselineValidationClientException(sb.toString());
    }


    // to be injected
    @Override
    public void setMessageTranslator(IVaselineMessageTranslator messageTranslator) {
        injectMessageTranslator(messageTranslator);
    }
}