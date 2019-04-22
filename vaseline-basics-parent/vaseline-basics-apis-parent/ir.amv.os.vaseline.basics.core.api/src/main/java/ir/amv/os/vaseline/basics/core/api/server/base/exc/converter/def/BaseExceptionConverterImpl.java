package ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

/**
 * @author Amir
 */
public class BaseExceptionConverterImpl<E extends Exception, CE extends BaseVaselineClientException>
        implements IDefaultExceptionConverter<E, CE> {

    private Class<E> exceptionClass;
    private Class<CE> clientExceptionClass;
    private IVaselineMessageTranslator messageTranslator;

    public BaseExceptionConverterImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), BaseExceptionConverterImpl.class);
        if (genericArgumentClasses != null) {
            exceptionClass = (Class<E>) genericArgumentClasses[0];
            clientExceptionClass = (Class<CE>) genericArgumentClasses[1];
        }
    }

    @Override
    public IVaselineMessageTranslator getMessageTranslator() {
        return messageTranslator;
    }

    @Override
    public Class<E> getExceptionClass() {
        return exceptionClass;
    }

    @Override
    public Class<CE> getClientExceptionClass() {
        return clientExceptionClass;
    }

    @Override
    public void setExceptionHandler(final ICoreExceptionHandler exceptionHandler) {
        injectExceptionHandler(exceptionHandler);
    }

    public void setMessageTranslator(final IVaselineMessageTranslator messageTranslator) {
        this.messageTranslator = messageTranslator;
    }
}
