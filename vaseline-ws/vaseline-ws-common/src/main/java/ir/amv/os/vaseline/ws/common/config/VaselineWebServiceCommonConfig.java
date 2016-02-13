package ir.amv.os.vaseline.ws.common.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by AMV on 2/13/2016.
 */
@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class VaselineWebServiceCommonConfig {

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

}
