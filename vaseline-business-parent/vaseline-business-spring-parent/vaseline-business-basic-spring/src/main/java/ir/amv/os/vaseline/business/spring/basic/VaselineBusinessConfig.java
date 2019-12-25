package ir.amv.os.vaseline.business.spring.basic;

import ir.amv.os.vaseline.business.spring.basic.exc.converter.UnknownBusinessExceptionConverterBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaselineBusinessConfig {

    /* Exception Conversion*/
    @Bean
    @ConditionalOnMissingBean(name = "unknownBusinessExceptionConverter")
    public UnknownBusinessExceptionConverterBean unknownBusinessExceptionConverter() {
        return new UnknownBusinessExceptionConverterBean();
    }
}
