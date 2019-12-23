package ir.amv.os.vaseline.security.authorization.basic.def.server.exc;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.ExceptionConversionException;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def.IDefaultExceptionConverter;
import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAccessDeniedException;
import ir.amv.os.vaseline.security.authorization.basic.api.shared.ExternalAccessDeniedException;

/**
 * @author Amir
 */
public interface IDefaultVaselineAccessDeniedExceptionConverter
        extends IDefaultExceptionConverter<VaselineAccessDeniedException, ExternalAccessDeniedException> {

    @Override
    default ExternalAccessDeniedException createClientException(VaselineAccessDeniedException exception, String message) throws ExceptionConversionException {
        ExternalAccessDeniedException clientException = IDefaultExceptionConverter.super.createClientException(exception, message);
        clientException.setActionTreeName(exception.getActionTreeName());
        clientException.setUsername(exception.getUsername());
        return clientException;
    }

    @Override
    default Object[] getInternationalizationArgs(final VaselineAccessDeniedException exception) {
        return new Object[]{exception.getUsername(), exception.getActionTreeName()};
    }
}
