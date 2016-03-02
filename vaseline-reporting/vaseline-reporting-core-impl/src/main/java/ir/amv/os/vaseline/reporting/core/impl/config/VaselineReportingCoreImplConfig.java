package ir.amv.os.vaseline.reporting.core.impl.config;

import ir.amv.os.vaseline.tasks.api.config.VaselineTaskManagementConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by AMV on 2/13/2016.
 */
@Configuration
@ComponentScan("ir.amv.os.vaseline.reporting.core.impl.server")
@Import({
        VaselineTaskManagementConfig.class
})
public class VaselineReportingCoreImplConfig {

}
