package ir.amv.os.vaseline.base.mapper.config;

import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AMV on 2/3/2016.
 */
@Configuration
public class VaselineMapperConfig {

    @Bean
    public Mapper dozerMapper(CustomFieldMapper customFieldMapper) {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        List<String> mappingFileUrls = Arrays.asList("dozer-main-config.xml");
        dozerBeanMapper.setMappingFiles(mappingFileUrls);
        dozerBeanMapper.setCustomFieldMapper(customFieldMapper);
        return dozerBeanMapper;
    }

}
