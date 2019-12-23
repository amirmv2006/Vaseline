package ir.amv.os.vaseline.business.basic.api.exc.converter;

import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.custom.IObjectConverter;
import ir.amv.os.vaseline.business.basic.api.exc.UnknownBusinessException;

public class UnknownBusinessExceptionConverter
        implements IObjectConverter<RuntimeException, UnknownBusinessException> {
    @Override
    public Class<RuntimeException> getSourceClass() {
        return RuntimeException.class;
    }

    @Override
    public Class<UnknownBusinessException> getDestinationClass() {
        return UnknownBusinessException.class;
    }

    @Override
    public UnknownBusinessException convert(RuntimeException source) {
        return new UnknownBusinessException(source);
    }
}
