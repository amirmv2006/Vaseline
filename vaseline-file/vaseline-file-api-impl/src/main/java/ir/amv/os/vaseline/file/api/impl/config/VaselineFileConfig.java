package ir.amv.os.vaseline.file.api.impl.config;

import ir.amv.os.vaseline.base.core.config.VaselineCoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@ComponentScan("ir.amv.os.vaseline.file.api.impl.server")
@Import(VaselineCoreConfig.class)
public class VaselineFileConfig {
}
