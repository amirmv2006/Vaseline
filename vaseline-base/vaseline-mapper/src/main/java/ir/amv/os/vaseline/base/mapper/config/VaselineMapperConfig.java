package ir.amv.os.vaseline.base.mapper.config;

import ir.amv.os.vaseline.base.core.config.VaselineCoreConfig;
import ir.amv.os.vaseline.base.core.api.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.base.mapper.config.custconv.IVaselineCustomConverterClassHolder;
import ir.amv.os.vaseline.base.mapper.config.fieldmapper.VaselineCustomFieldMapper;
import ir.amv.os.vaseline.base.mapper.server.annot.ExcludeFromDozer;
import ir.amv.os.vaseline.base.mapper.server.annot.VaselineMapTo;
import org.dozer.CustomConverter;
import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;
import org.springframework.context.annotation.*;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/3/2016.
 */
@Configuration
@Import(VaselineCoreConfig.class)
@ComponentScan("ir.amv.os.vaseline.base.mapper.server")
public class VaselineMapperConfig {

    @Bean
    public Mapper dozerMapper(
            VaselineCustomFieldMapper customFieldMapper,
            List<IVaselinePolymorphysmClassHolder> polymorphysmClassHolders,
            List<IVaselineCustomConverterClassHolder> customConverters) {
        VaselineDozerMapper dozerBeanMapper = new VaselineDozerMapper();
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
        return dozerBeanMapper;
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
