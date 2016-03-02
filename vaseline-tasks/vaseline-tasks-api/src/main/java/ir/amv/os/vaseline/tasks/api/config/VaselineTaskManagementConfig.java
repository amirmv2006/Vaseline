package ir.amv.os.vaseline.tasks.api.config;

import ir.amv.os.vaseline.tasks.api.config.executor.PriorityExecutor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.config.TaskManagementConfigUtils;

import java.util.concurrent.Executor;

/**
 * Created by AMV on 2/13/2016.
 */
@Configuration
@EnableAsync
public class VaselineTaskManagementConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new PriorityExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Bean(name = TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public ir.amv.os.vaseline.tasks.api.config.postprocessor.AsyncAnnotationBeanPostProcessor asyncAdvisor() {
        ir.amv.os.vaseline.tasks.api.config.postprocessor.AsyncAnnotationBeanPostProcessor bpp = new ir.amv.os.vaseline.tasks.api.config.postprocessor.AsyncAnnotationBeanPostProcessor();
        if (this.getAsyncExecutor() != null) {
            bpp.setExecutor(this.getAsyncExecutor());
        }
        if (this.getAsyncUncaughtExceptionHandler() != null) {
            bpp.setExceptionHandler(this.getAsyncUncaughtExceptionHandler());
        }
        bpp.setProxyTargetClass(true);
        bpp.setOrder(Integer.MAX_VALUE);
        return bpp;
    }
}
