package ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;

public interface IBaseExceptionConverter<E extends Exception, CE extends BaseVaselineClientException> {

	CE convertException(E exception);
}
