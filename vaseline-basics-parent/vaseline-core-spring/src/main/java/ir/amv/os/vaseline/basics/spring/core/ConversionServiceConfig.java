package ir.amv.os.vaseline.basics.spring.core;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class ConversionServiceConfig {

    @Bean
    @ConditionalOnNotWebApplication
    public ConversionService conversionService(
            ConfigurableBeanFactory env) {
        return env.getConversionService();
    }

}
