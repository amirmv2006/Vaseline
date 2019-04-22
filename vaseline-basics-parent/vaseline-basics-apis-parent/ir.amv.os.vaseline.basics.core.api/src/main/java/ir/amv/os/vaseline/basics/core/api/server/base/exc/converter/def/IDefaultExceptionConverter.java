package ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.ExceptionConversionException;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;

/**
 * @author Amir
 */
public interface IDefaultExceptionConverter<E extends Exception, CE extends BaseVaselineClientException>
        extends IBaseExceptionConverter<E, CE> {

    IVaselineMessageTranslator getMessageTranslator();
    Class<E> getExceptionClass();
    Class<CE> getClientExceptionClass();
    void setExceptionHandler(ICoreExceptionHandler exceptionHandler);

    default void injectExceptionHandler(ICoreExceptionHandler exceptionHandler) {
        exceptionHandler.registerExceptionConverter(getExceptionClass(), this);
    }

    @Override
    default CE convertException(E exception) throws ExceptionConversionException {
        String message = getMessage(exception);
        return createClientException(exception, message);
    }

    default String getMessage(E exception) {
        return exception.getMessage();
    }

    default CE createClientException(E exception, String message) throws ExceptionConversionException {
        try {
            String translatedMessage = message;
            try {
                translatedMessage = getMessageTranslator().getMessage(message, getInternationalizationArgs(exception));
            } catch (Exception ignored) {
            }
            return getClientExceptionClass().getConstructor(String.class).newInstance(translatedMessage);
        } catch (Exception e) {
            throw new ExceptionConversionException(message);
        }
    }

    default Object[] getInternationalizationArgs(final E exception) {
        return new Object[]{};
    }

}
