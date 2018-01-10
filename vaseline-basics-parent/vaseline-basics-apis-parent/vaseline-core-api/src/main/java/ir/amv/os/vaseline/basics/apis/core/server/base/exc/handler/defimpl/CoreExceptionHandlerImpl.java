package ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

	@Override
	public void registerExceptionConverter(Class<? extends Throwable> exceptionClass, IBaseExceptionConverter<?, ?> exceptionHandler) {
		exceptionHandlerMap.put(exceptionClass, exceptionHandler);
	}

}
