package ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.def;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.defimpl.IBaseImplementedExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;

public class DefaultExceptionConverter
        implements IBaseImplementedExceptionConverter<Exception, BaseVaselineClientException> {

    private IVaselineMessageTranslator messageTranslator;

    @Override
    public IVaselineMessageTranslator getMessageTranslator() {
        return messageTranslator;
    }

    @Override
    public Class<Exception> getExceptionClass() {
        return Exception.class;
    }

    @Override
    public Class<BaseVaselineClientException> getClientExceptionClass() {
        return BaseVaselineClientException.class;
    }

    @Override
    public void setExceptionHandler(final ICoreExceptionHandler exceptionHandler) {
        injectExceptionHandler(exceptionHandler);
    }

    public void setMessageTranslator(final IVaselineMessageTranslator messageTranslator) {
        this.messageTranslator = messageTranslator;
    }
}
