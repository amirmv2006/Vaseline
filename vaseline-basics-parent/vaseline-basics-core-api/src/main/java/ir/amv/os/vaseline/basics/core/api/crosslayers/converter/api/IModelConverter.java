package ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api;

public interface IModelConverter<M, N> extends IConverterApi {

    N convertTo(M source);

    M convertFrom(N source);

}
