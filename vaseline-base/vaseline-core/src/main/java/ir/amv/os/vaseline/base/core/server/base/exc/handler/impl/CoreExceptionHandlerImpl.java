package ir.amv.os.vaseline.base.core.server.base.exc.handler.impl;

import ir.amv.os.vaseline.basics.apis.core.api.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.api.shared.base.exc.BaseVaselineClientException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class CoreExceptionHandlerImpl implements ICoreExceptionHandler {

	private Map<Class<? extends Throwable>, IBaseExceptionConverter<?, ?>> exceptionHandlerMap = new HashMap<Class<? extends Throwable>, IBaseExceptionConverter<?, ?>>();
	private Map<String, Throwable> businessExceptionsMap = new HashMap<String, Throwable>();

	@SuppressWarnings("unchecked")
	@Override
	public BaseVaselineClientException convertException(Exception exception) {
		exception.printStackTrace();
		Class<? extends Throwable> exceptionClass = exception.getClass();
		IBaseExceptionConverter<Exception, BaseVaselineClientException> exceptionHandler = (IBaseExceptionConverter<Exception, BaseVaselineClientException>) exceptionHandlerMap.get(exceptionClass);
		while (exceptionHandler == null) {
			exceptionClass = (Class<? extends Throwable>) exceptionClass.getSuperclass();
			exceptionHandler = (IBaseExceptionConverter<Exception, BaseVaselineClientException>) exceptionHandlerMap.get(exceptionClass);
		}
		BaseVaselineClientException clientException = exceptionHandler.convertException(exception);
		String identifier = generateIdentifier(exception, clientException);
		clientException.setIdentifier(identifier);
		businessExceptionsMap.put(identifier, exception);
		return clientException;
	}

	public Throwable getException(String identifier) {
		return businessExceptionsMap.get(identifier);
	}
	
	private String generateIdentifier(Throwable throwable, BaseVaselineClientException clientException) {
		return UUID.randomUUID().toString();
	}

	public void registerHandler(Class<? extends Throwable> exceptionClass, IBaseExceptionConverter<?, ?> exceptionHandler) {
		exceptionHandlerMap.put(exceptionClass, exceptionHandler);
	}

}
