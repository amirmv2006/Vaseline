package ir.amv.os.vaseline.ws.spring.rest.jersey;

import ir.amv.os.vaseline.basics.apis.json.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.ws.spring.rest.jersey.application.VaselineRestApplication;
import ir.amv.os.vaseline.ws.spring.rest.jersey.multiparam.msgrw.VaselineSpringMultiparamHandler;
import org.glassfish.jersey.server.ResourceConfig;
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
