package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api;

import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api.IConverterApi;
import org.springframework.core.convert.ConversionService;

public interface IDefaultConverterSpringApi extends IConverterApi {

    ConversionService getConversionService();

    @Override
    default <T> T convert(Object source, Class<T> targetType) {
        return getConversionService().convert(source, targetType);
    }
}
