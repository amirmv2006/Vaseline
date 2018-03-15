package ir.amv.os.vaseline.security.osgi.authorization.basic.exc;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.def.BaseExceptionConverterImpl;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.def.DefaultExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.exception.VaselineAccessDeniedException;
import ir.amv.os.vaseline.security.apis.authorization.basic.shared.VaselineClientAccessDeniedException;
import ir.amv.os.vaseline.security.apis.authorization.basicimpl.server.exc.IImplementedVaselineAccessDeniedExceptionConverter;
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
