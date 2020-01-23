package ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api;

public interface IConverterApi {

    <T> T convert(Object source, Class<T> targetType);
}
