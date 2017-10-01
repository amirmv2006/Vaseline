package ir.amv.os.vaseline.basics.spring.validation.config;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.defimpl.CoreExceptionHandlerImpl;
import ir.amv.os.vaseline.basics.apis.i18n.server.message.translator.IVaselineMessageTranslator;
import ir.amv.os.vaseline.basics.apis.validation.server.exc.converter.VaselineValidationExceptionConverterImpl;
import ir.amv.os.vaseline.basics.spring.core.config.VaselineCoreConfig;
import ir.amv.os.vaseline.basics.spring.i18n.config.VaselineI18nConfig;
import ir.amv.os.vaseline.basics.spring.validation.config.weblogic.patch.JPAIgnoreTraversableResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@Import({
        VaselineCoreConfig.class,
        VaselineI18nConfig.class
})
public class VaselineValidationConfig {

    @Bean
    public JPAIgnoreTraversableResolver traversableResolver() {
        return new JPAIgnoreTraversableResolver();
    }

    @Bean
    public LocalValidatorFactoryBean getValidatorFactory(MessageSource messageSource) {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setTraversableResolver(traversableResolver());
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        localValidatorFactoryBean.getValidationPropertyMap().put(
                "hibernate.validator.fail_fast", "true");
        return localValidatorFactoryBean;
    }

    @Bean
    public VaselineValidationExceptionConverterImpl vaselineValidationExceptionConverter(
            CoreExceptionHandlerImpl exceptionHandler,
            IVaselineMessageTranslator messageTranslator
    ) {
        VaselineValidationExceptionConverterImpl exceptionConverter = new VaselineValidationExceptionConverterImpl(exceptionHandler);
        exceptionConverter.setMessageTranslator(messageTranslator);
        return exceptionConverter;
    }
}
