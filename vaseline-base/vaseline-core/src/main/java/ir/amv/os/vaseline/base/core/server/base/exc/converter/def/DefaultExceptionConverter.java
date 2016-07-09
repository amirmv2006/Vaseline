package ir.amv.os.vaseline.base.core.server.base.exc.converter.def;

import ir.amv.os.vaseline.base.core.server.base.exc.converter.impl.BaseExceptionConverterImpl;
import ir.amv.os.vaseline.base.core.server.base.exc.handler.impl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultExceptionConverter extends BaseExceptionConverterImpl<Exception, BaseVaselineClientException> {

	@Autowired
	public DefaultExceptionConverter(CoreExceptionHandlerImpl exceptionHandler) {
		super(exceptionHandler);
	}

}
