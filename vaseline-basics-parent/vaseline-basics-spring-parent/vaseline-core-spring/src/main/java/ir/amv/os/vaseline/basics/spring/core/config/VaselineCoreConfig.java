package ir.amv.os.vaseline.basics.spring.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
