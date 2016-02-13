package ir.amv.os.vaseline.base.validation.config;

import ir.amv.os.vaseline.base.i18n.config.VaselineI18nConfig;
import ir.amv.os.vaseline.base.validation.config.weblogic.patch.JPAIgnoreTraversableResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@Import(VaselineI18nConfig.class)
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

}
