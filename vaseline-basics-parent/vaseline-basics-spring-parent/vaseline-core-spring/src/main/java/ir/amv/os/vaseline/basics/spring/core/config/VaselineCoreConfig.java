package ir.amv.os.vaseline.basics.spring.core.config;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.def.DefaultExceptionConverter;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.defimpl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.spring.i18n.config.VaselineI18nConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by AMV on 2/2/2016.
 */
@Configuration
@PropertySource("classpath:vaseline.properties")
@ComponentScan("ir.amv.os.vaseline.basics.spring.core.server")
@Import(VaselineI18nConfig.class)
public class VaselineCoreConfig implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /* Exception Handling*/
    @Bean
    public CoreExceptionHandlerImpl exceptionHandler() {
        return new CoreExceptionHandlerImpl();
    }

    @Bean
    public DefaultExceptionConverter defaultExceptionConverter(CoreExceptionHandlerImpl coreExceptionHandler, IVaselineMessageTranslator messageTranslator) {
        DefaultExceptionConverter defaultExceptionConverter = new DefaultExceptionConverter();
        defaultExceptionConverter.setExceptionHandler(coreExceptionHandler);
        defaultExceptionConverter.setMessageTranslator(messageTranslator);
        return defaultExceptionConverter;
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
