package ir.amv.os.vaseline.file.db.impl.config;

import ir.amv.os.vaseline.file.api.impl.config.VaselineFileConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@Import(VaselineFileConfig.class)
@ComponentScan("ir.amv.os.vaseline.file.db.impl.server")
public class VaselineFileDbConfig {
}
