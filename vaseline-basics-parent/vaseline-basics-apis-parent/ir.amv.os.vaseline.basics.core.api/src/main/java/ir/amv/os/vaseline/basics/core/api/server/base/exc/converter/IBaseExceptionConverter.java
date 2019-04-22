package ir.amv.os.vaseline.basics.core.api.server.base.exc.converter;

import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;

public interface IBaseExceptionConverter<E extends Exception, CE extends BaseVaselineClientException> {

	CE convertException(E exception) throws ExceptionConversionException;
}
