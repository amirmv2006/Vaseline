package ir.amv.os.vaseline.basics.spring.core;

import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl.DefaultObjectDozerConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

/**
 * Created by AMV on 2/2/2016.
 */
@Configuration
public class VaselineCoreConfig {
    @Bean
    @ConditionalOnMissingBean(name = "proxyAwareBeanPostProcessor")
    public ProxyAwareBeanPostProcessor proxyAwareBeanPostProcessor() {
        return new ProxyAwareBeanPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean(ConversionService.class)
    public ConversionService conversionService(Set<GenericConverter> converters) {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(converters);
        bean.afterPropertiesSet();
        return bean.getObject();
    }

    @Bean
    public DefaultObjectDozerConverter dozerConverter() {
        return new DefaultObjectDozerConverter();
    }
}
