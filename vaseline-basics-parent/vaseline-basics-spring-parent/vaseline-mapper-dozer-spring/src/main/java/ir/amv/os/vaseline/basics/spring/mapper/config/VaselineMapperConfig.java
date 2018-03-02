package ir.amv.os.vaseline.basics.spring.mapper.config;

import ir.amv.os.vaseline.basics.apis.mapper.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.apis.mapper.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.basics.spring.core.config.VaselineCoreConfig;
import ir.amv.os.vaseline.basics.spring.mapper.config.custconv.IVaselineCustomConverterClassHolder;
import ir.amv.os.vaseline.basics.spring.mapper.config.fieldmapper.VaselineCustomFieldMapper;
import ir.amv.os.vaseline.basics.apis.mapper.shared.annot.ExcludeFromDozer;
import ir.amv.os.vaseline.basics.apis.mapper.shared.annot.VaselineMapTo;
import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import org.dozer.CustomFieldMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AMV on 2/3/2016.
 */
@Configuration
@Import(VaselineCoreConfig.class)
public class VaselineMapperConfig {

    @Bean
    public IVaselineObjectMapper vaselineObjectMapper(
            VaselineCustomFieldMapper customFieldMapper,
            List<IVaselinePolymorphysmClassHolder> polymorphysmClassHolders,
            List<IVaselineCustomConverterClassHolder> customConverters) {
        final VaselineDozerMapper dozerBeanMapper = new VaselineDozerMapper();
        List<String> mappingFileUrls = Arrays.asList("dozer-main-config.xml");
        dozerBeanMapper.setMappingFiles(mappingFileUrls);
        dozerBeanMapper.setCustomFieldMapper(customFieldMapper);
        List<BeanMappingBuilder> mappings = new ArrayList<BeanMappingBuilder>();
        for (IVaselinePolymorphysmClassHolder polymorphysmClassHolder : polymorphysmClassHolders) {
            List<Class<?>> parentClasses = polymorphysmClassHolder.parentClasses();
            for (Class<?> parentClass : parentClasses) {
                if (parentClass.isAnnotationPresent(ExcludeFromDozer.class)) {
                    continue;
                }
                XmlSeeAlso seeAlso = parentClass.getAnnotation(XmlSeeAlso.class);
                addMappingsFor(mappings, parentClass);
                if (seeAlso != null) {
                    Class[] childClasses = seeAlso.value();
                    for (int i = 0; i < childClasses.length; i++) {
                        addMappingsFor(mappings, childClasses[i]);
                    }
                }
            }
        }
        List<Class<?>> customConvertersClasses = new ArrayList<Class<?>>();
        for (IVaselineCustomConverterClassHolder customConverter : customConverters) {
            customConvertersClasses.addAll(Arrays.asList(customConverter.customConverterClasses()));
        }
        dozerBeanMapper.setCustomConvertersClasses(customConvertersClasses);
        dozerBeanMapper.setMappings(mappings);
        return new IVaselineObjectMapper() {
            @Override
            public <D> D map(Object source, Class<D> destinationClass) throws VaselineConvertException {
                try {
                    return dozerBeanMapper.map(source, destinationClass);
                } catch (MappingException e) {
                    throw new VaselineConvertException(e);
                }
            }

            @Override
            public void map(Object source, Object destination) throws VaselineConvertException {
                try {
                    dozerBeanMapper.map(source, destination);
                } catch (MappingException e) {
                    throw new VaselineConvertException(e);
                }
            }
        };
    }

    public static class VaselineMappingBuilder extends BeanMappingBuilder {
        private Class<?> sourceClass;
        private Class<?> destinationClass;
        private TypeMappingOption[] options;

        public VaselineMappingBuilder(Class<?> sourceClass, Class<?> destinationClass, TypeMappingOption... options) {
            this.sourceClass = sourceClass;
            this.destinationClass = destinationClass;
            this.options = options;
        }

        @Override
        protected void configure() {
            mapping(sourceClass, destinationClass, options);
        }
    }
    private void addMappingsFor(List<BeanMappingBuilder> mappings, Class<?> parentClass) {
        VaselineMapTo vaselineMapTo = parentClass.getAnnotation(VaselineMapTo.class);
        if (vaselineMapTo != null) {
            mappings.add(new VaselineMappingBuilder(parentClass, vaselineMapTo.mapToServerClass()));
        }
    }

    @Bean
    @Conditional(NotExistingCustomFieldMapper.class)
    public CustomFieldMapper dummyFieldMapper() {
        return new CustomFieldMapper() {
            @Override
            public boolean mapField(Object source, Object destination, Object sourceFieldValue, ClassMap classMap, FieldMap fieldMapping) {
                return false;
            }
        };
    }

    @Bean
    public VaselineCustomFieldMapper customFieldMapper(List<CustomFieldMapper> customFieldMapper) {
        return new VaselineCustomFieldMapper(customFieldMapper);
    }
}
