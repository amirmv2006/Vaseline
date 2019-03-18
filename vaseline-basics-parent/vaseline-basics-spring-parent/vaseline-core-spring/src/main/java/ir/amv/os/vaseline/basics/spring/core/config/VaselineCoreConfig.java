package ir.amv.os.vaseline.basics.spring.core.config;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def.MainExceptionConverter;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.defimpl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.basics.i18n.api.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.spring.core.server.proxyaware.beanpostprocessor.ProxyAwareBeanPostProcessor;
import ir.amv.os.vaseline.basics.spring.i18n.config.VaselineI18nConfig;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by AMV on 2/2/2016.
 */
@Configuration
@Import(VaselineI18nConfig.class)
public class VaselineCoreConfig implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConditionalOnMissingBean(name = "proxyAwareBeanPostProcessor")
    public ProxyAwareBeanPostProcessor proxyAwareBeanPostProcessor() {
        return new ProxyAwareBeanPostProcessor();
    }

    /* Exception Handling*/
    @Bean
    @ConditionalOnMissingBean(ICoreExceptionHandler.class)
    public CoreExceptionHandlerImpl exceptionHandler() {
        return new CoreExceptionHandlerImpl();
    }

    @Bean
    @ConditionalOnMissingBean(name = "defaultExceptionConverter")
    public MainExceptionConverter defaultExceptionConverter(CoreExceptionHandlerImpl coreExceptionHandler, IVaselineMessageTranslator messageTranslator) {
        MainExceptionConverter mainExceptionConverter = new MainExceptionConverter();
        mainExceptionConverter.setExceptionHandler(coreExceptionHandler);
        mainExceptionConverter.setMessageTranslator(messageTranslator);
        return mainExceptionConverter;
    }

    /**
     * returns bean of <code>beanClass</code>
     * @param beanClass
     * @param <T>
     * @return
     * @deprecated Avoid using this AFAP (i.e As Far As Possible)
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

}
