package ir.amv.os.vaseline.base.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by AMV on 2/2/2016.
 */
@Configuration
@PropertySource("classpath:vaseline.properties")
@ComponentScan("ir.amv.os.vaseline.base.core.server")
public class VaselineCoreConfig {
}
