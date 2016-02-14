package ir.amv.os.vaseline.reporting.async.rest.config;

import ir.amv.os.vaseline.ws.rest.config.VaselineWebServiceRestConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by AMV on 2/14/2016.
 */
@Configuration
@Import(
        VaselineWebServiceRestConfig.class
)
public class VaselineReportingAsyncRestConfig {
}
