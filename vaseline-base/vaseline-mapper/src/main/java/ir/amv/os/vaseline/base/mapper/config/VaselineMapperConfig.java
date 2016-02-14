package ir.amv.os.vaseline.base.mapper.config;

import ir.amv.os.vaseline.base.core.config.VaselineCoreConfig;
import ir.amv.os.vaseline.base.mapper.config.fieldmapper.VaselineCustomFieldMapper;
import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.springframework.context.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AMV on 2/3/2016.
 */
@Configuration
@Import(VaselineCoreConfig.class)
@ComponentScan("ir.amv.os.vaseline.base.mapper.server")
public class VaselineMapperConfig {

    @Bean
    public Mapper dozerMapper(VaselineCustomFieldMapper customFieldMapper) {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        List<String> mappingFileUrls = Arrays.asList("dozer-main-config.xml");
        dozerBeanMapper.setMappingFiles(mappingFileUrls);
        dozerBeanMapper.setCustomFieldMapper(customFieldMapper);
        return dozerBeanMapper;
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
