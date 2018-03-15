package ir.amv.os.vaseline.security.apis.authorization.basicimpl.server.exc;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.defimpl.IBaseImplementedExceptionConverter;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAccessDeniedException;
import ir.amv.os.vaseline.security.apis.authorization.basic.shared.VaselineClientAccessDeniedException;

/**
 * @author Amir
 */
public interface IImplementedVaselineAccessDeniedExceptionConverter
        extends IBaseImplementedExceptionConverter<VaselineAccessDeniedException, VaselineClientAccessDeniedException>{

    @Override
    default VaselineClientAccessDeniedException createClientException(VaselineAccessDeniedException exception, String message) {
        VaselineClientAccessDeniedException clientException = IBaseImplementedExceptionConverter.super.createClientException(exception, message);
        clientException.setActionTreeName(exception.getActionTreeName());
        clientException.setUsername(exception.getUsername());
        return clientException;
    }

    @Override
    default Object[] getInternationalizationArgs(final VaselineAccessDeniedException exception) {
        return new Object[]{exception.getUsername(), exception.getActionTreeName()};
    }
}
