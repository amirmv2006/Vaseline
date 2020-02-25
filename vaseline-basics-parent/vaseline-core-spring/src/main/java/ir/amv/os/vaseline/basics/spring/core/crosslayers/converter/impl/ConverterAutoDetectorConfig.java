package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

@Configuration
public class ConverterAutoDetectorConfig {

    @Bean
    @ConditionalOnMissingBean(type = "org.springframework.core.convert.ConversionService")
    public ConversionService vaselineConversionSvc(AutoDetectedGenericConverters autoDetected) {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(autoDetected.getConverters());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
