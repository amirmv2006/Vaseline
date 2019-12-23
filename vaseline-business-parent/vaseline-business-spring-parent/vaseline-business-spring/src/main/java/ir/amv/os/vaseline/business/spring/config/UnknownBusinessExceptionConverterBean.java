package ir.amv.os.vaseline.business.spring.config;

import ir.amv.os.vaseline.business.basic.api.exc.UnknownBusinessException;
import ir.amv.os.vaseline.business.basic.api.exc.converter.UnknownBusinessExceptionConverter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Collections;
import java.util.Set;

public class UnknownBusinessExceptionConverterBean
        extends UnknownBusinessExceptionConverter
        implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(RuntimeException.class, UnknownBusinessException.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return convert((RuntimeException) source);
    }
}
