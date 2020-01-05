package ir.amv.os.vaseline.business.spring.basic.exc.converter;

import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl.AutoDetectableGenericConverter;
import ir.amv.os.vaseline.business.basic.api.exc.UnknownBusinessException;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Collections;
import java.util.Set;

public class UnknownBusinessExceptionConverterBean
        implements AutoDetectableGenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(RuntimeException.class, UnknownBusinessException.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return new UnknownBusinessException((RuntimeException) source);
    }
}
