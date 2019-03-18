package ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;

public class MainExceptionConverter
        implements IDefaultExceptionConverter<Exception, BaseVaselineClientException> {

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
