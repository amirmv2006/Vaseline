package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.basics.core.api.utils.hibernate.HibernateUtils;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DefaultObjectDozerConverter
        implements GenericConverter {

    private final Mapper mapper;

    public DefaultObjectDozerConverter() {
        mapper = DozerBeanMapperBuilder.create()
                .withCustomFieldMapper(
                        (source, destination, sourceFieldValue, classMap, fieldMapping)
                                -> !HibernateUtils.isLazyObjectInitialized(sourceFieldValue)
                )
                .build();
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return new HashSet<>(Arrays.asList(
                new ConvertiblePair(IBaseDto.class, IBaseBusinessModel.class),
                new ConvertiblePair(IBaseBusinessModel.class, IBasePersistenceModel.class),
                new ConvertiblePair(IBasePersistenceModel.class, IBaseBusinessModel.class),
                new ConvertiblePair(IBaseBusinessModel.class, IBaseDto.class)
        ));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return mapper.map(source, targetType.getType());
    }
}