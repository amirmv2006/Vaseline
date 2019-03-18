package ir.amv.os.vaseline.basics.core.api.server.base.exc.handler;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.IBaseExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;

public interface ICoreExceptionHandler {

	BaseVaselineClientException convertException(Exception exception);

	void registerExceptionConverter(Class<? extends Throwable> exceptionClass, IBaseExceptionConverter<?, ?> exceptionHandler);
}
