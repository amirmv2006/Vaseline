package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api;

import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api.IConverterApi;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;

public interface IModelConverter<M, N> extends IConverterApi {

    default N convertTo(M source) {
        Class<N> generic = GenericUtils.getGeneric(getClass(), IModelConverter.class, 1);
        return convert(source, generic);
    }

    default M convertFrom(N source) {
        Class<M> generic = GenericUtils.getGeneric(getClass(), IModelConverter.class, 0);
        return convert(source, generic);
    }

}
