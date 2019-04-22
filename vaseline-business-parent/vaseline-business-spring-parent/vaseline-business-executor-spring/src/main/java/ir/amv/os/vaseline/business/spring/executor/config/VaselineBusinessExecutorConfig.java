package ir.amv.os.vaseline.business.spring.executor.config;

import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessExecutorInterceptor;
import ir.amv.os.vaseline.business.spring.executor.server.DummyVaselineBusinessExecutorInterceptorImpl;
import ir.amv.os.vaseline.business.spring.executor.server.VaselineBusinessExecutorSpringImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
public class VaselineBusinessExecutorConfig {

    @Bean
    @ConditionalOnMissingBean(IVaselineBusinessExecutorInterceptor.class)
    public IVaselineBusinessExecutorInterceptor vaselineBusinessActionInterceptor() {
        return new DummyVaselineBusinessExecutorInterceptorImpl();
    }

    @Bean
    @ConditionalOnMissingBean(IVaselineBusinessActionExecutor.class)
    public IVaselineBusinessActionExecutor vaselineBusinessActionExecutor() {
        return new VaselineBusinessExecutorSpringImpl();
    }
}
