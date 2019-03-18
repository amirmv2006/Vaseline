package ir.amv.os.vaseline.security.authorization.basic.osgi.exc;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def.BaseExceptionConverterImpl;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.security.authorization.basic.api.server.exception.VaselineAccessDeniedException;
import ir.amv.os.vaseline.security.authorization.basic.api.shared.VaselineClientAccessDeniedException;
import ir.amv.os.vaseline.security.authorization.basic.def.server.exc.IImplementedVaselineAccessDeniedExceptionConverter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IBaseExceptionConverter.class
)
public class VaselineOsgiAccessDeniedExceptionConverter
        extends BaseExceptionConverterImpl<VaselineAccessDeniedException, VaselineClientAccessDeniedException>
        implements IImplementedVaselineAccessDeniedExceptionConverter {

    @Override
    @Reference
    public void setExceptionHandler(final ICoreExceptionHandler exceptionHandler) {
        super.setExceptionHandler(exceptionHandler);
    }

    @Override
    @Reference
    public void setMessageTranslator(final IVaselineMessageTranslator messageTranslator) {
        super.setMessageTranslator(messageTranslator);
    }
}
