package ir.amv.os.vaseline.base.core.api.server.base.exc.handler;

import ir.amv.os.vaseline.base.core.api.shared.base.exc.BaseVaselineClientException;

public interface ICoreExceptionHandler {

	BaseVaselineClientException convertException(Exception exception);
}
