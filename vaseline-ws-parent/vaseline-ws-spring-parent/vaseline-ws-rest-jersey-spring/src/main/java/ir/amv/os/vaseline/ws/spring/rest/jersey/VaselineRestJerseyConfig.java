package ir.amv.os.vaseline.ws.spring.rest.jersey;

import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.ws.spring.rest.jersey.multiparam.msgrw.VaselineSpringMultiparamHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
@ComponentScan("ir.amv.os.vaseline.ws.spring.rest.jersey")
public class VaselineRestJerseyConfig {

    @Bean
    @ConditionalOnMissingBean(name = "multiparamAnnotJsonReaderWriter")
    public VaselineSpringMultiparamHandler multiparamAnnotJsonReaderWriter(final IVaselineJsonConverter jsonConverter) {
        return new VaselineSpringMultiparamHandler(jsonConverter);
    }
}
