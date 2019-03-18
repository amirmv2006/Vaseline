package ir.amv.os.vaseline.basics.core.osgi.exc.converter;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def.MainExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IBaseExceptionConverter.class
)
public class VaselineOsgiMainExceptionConverterImpl
        extends MainExceptionConverter
        implements IBaseExceptionConverter<Exception, BaseVaselineClientException>{

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
