package ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.def;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.defimpl.BaseExceptionConverterImpl;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.defimpl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;

public class DefaultExceptionConverter extends BaseExceptionConverterImpl<Exception, BaseVaselineClientException> {

	public DefaultExceptionConverter(CoreExceptionHandlerImpl exceptionHandler) {
		super(exceptionHandler);
	}

	@Override
	public void setMessageTranslator(IVaselineMessageTranslator messageTranslator) {
		injectMessageTranslator(messageTranslator);
	}

}
