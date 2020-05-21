package ir.amv.os.vaseline.basics.spring.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by AMV on 2/2/2016.
 */
@Configuration
@Import(ConversionServiceConfig.class)
public class VaselineCoreConfig {

    @Bean
    @ConditionalOnMissingBean(name = "proxyAwareBeanPostProcessor")
    public ProxyAwareBeanPostProcessor proxyAwareBeanPostProcessor() {
        return new ProxyAwareBeanPostProcessor();
    }

}
