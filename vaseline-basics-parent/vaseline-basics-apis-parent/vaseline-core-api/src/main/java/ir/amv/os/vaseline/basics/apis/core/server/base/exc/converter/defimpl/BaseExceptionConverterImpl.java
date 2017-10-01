package ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.defimpl;

import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.defimpl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.util.Locale;

public abstract class BaseExceptionConverterImpl<E extends Exception, CE extends BaseVaselineClientException> implements IBaseExceptionConverter<E, CE> {

	protected Class<E> exceptionClass;
	protected Class<CE> clientExceptionClass;
	protected IVaselineMessageTranslator messageTranslator;

	@SuppressWarnings("unchecked")
	public BaseExceptionConverterImpl(CoreExceptionHandlerImpl exceptionHandler) {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            exceptionClass = (Class<E>)genericArgumentClasses[0];
            clientExceptionClass = (Class<CE>)genericArgumentClasses[1];
        } else {
			throw new RuntimeException("Can not find the exception class, did you specify the generics for BaseExceptionConverterImpl?");
		}
		exceptionHandler.registerHandler(exceptionClass, this);
	}

	@Override
	public CE convertException(E exception) {
		String message = getMessage(exception);
		CE ce = createClientException(exception, message);
		return ce;
	}

	protected String getMessage(E exception) {
		return exception.getMessage();
	}

	protected CE createClientException(E exception, String message) {
		try {
			String translatedMessage = message;
			try {
				Locale locale = getCurrentLocale();
				translatedMessage = messageTranslator.getMessage(message, new Object[]{}, locale);
			} catch (Exception e) {
			}
			CE newInstance = clientExceptionClass.getConstructor(String.class).newInstance(translatedMessage);
			return newInstance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Locale getCurrentLocale() {
		return new Locale("fa")/*LocaleContextHolder.getLocale()*/;
	}

	protected void injectMessageTranslator(IVaselineMessageTranslator messageSource) {
		this.messageTranslator = messageSource;
	}

	public abstract void setMessageTranslator(IVaselineMessageTranslator messageTranslator);
}
