package ir.amv.os.vaseline.basics.spring.core.crosslayers;

import ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.impl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultModelConverter;
import org.springframework.core.convert.ConversionService;

public class GenericModelConverterLayer<M, N>
        extends ProxyAwareImpl
        implements IDefaultModelConverter<M, N> {

    private final ConversionService conversionService;

    public GenericModelConverterLayer(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return conversionService.convert(source, targetType);
    }
}
