package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api;

import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api.IConverterApi;
import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api.IModelConverter;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;

public interface IDefaultModelConverter<M, N> extends IModelConverter<M, N> {

    @Override
    default N convertTo(M source) {
        Class<N> generic = GenericUtils.getGeneric(getClass(), IDefaultModelConverter.class, 1);
        return convert(source, generic);
    }

    @Override
    default M convertFrom(N source) {
        Class<M> generic = GenericUtils.getGeneric(getClass(), IDefaultModelConverter.class, 0);
        return convert(source, generic);
    }

}
