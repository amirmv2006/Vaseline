package ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;

import java.util.Locale;

/**
 * @author Amir
 */
public interface IBaseImplementedExceptionConverter<E extends Exception, CE extends BaseVaselineClientException>
        extends IBaseExceptionConverter<E, CE> {

    IVaselineMessageTranslator getMessageTranslator();
    Class<E> getExceptionClass();
    Class<CE> getClientExceptionClass();
    void setExceptionHandler(ICoreExceptionHandler exceptionHandler);

    default void injectExceptionHandler(ICoreExceptionHandler exceptionHandler) {
        exceptionHandler.registerExceptionConverter(getExceptionClass(), this);
    }

    @Override
    default CE convertException(E exception) {
        String message = getMessage(exception);
        CE ce = createClientException(exception, message);
        return ce;
    }

    default String getMessage(E exception) {
        return exception.getMessage();
    }

    default CE createClientException(E exception, String message) {
        try {
            String translatedMessage = message;
            try {
                Locale locale = getCurrentLocale();
                translatedMessage = getMessageTranslator().getMessage(message, new Object[]{}, locale);
            } catch (Exception e) {
            }
            CE newInstance = getClientExceptionClass().getConstructor(String.class).newInstance(translatedMessage);
            return newInstance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    default Locale getCurrentLocale() {
        return new Locale("fa")/*LocaleContextHolder.getLocale()*/;
    }

}
