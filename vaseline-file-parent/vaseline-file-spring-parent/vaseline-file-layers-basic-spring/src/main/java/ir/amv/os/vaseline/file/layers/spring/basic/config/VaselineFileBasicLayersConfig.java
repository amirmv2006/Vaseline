package ir.amv.os.vaseline.file.layers.spring.basic.config;

import ir.amv.os.vaseline.business.spring.executor.config.VaselineBusinessExecutorConfig;
import ir.amv.os.vaseline.file.business.api.IVaselineFileApi;
import ir.amv.os.vaseline.file.service.api.IVaselineFileService;
import ir.amv.os.vaseline.file.layers.spring.basic.server.api.VaselineFileApiImpl;
import ir.amv.os.vaseline.file.layers.spring.basic.server.service.VaselineFileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Amir
 */
@Configuration
@Import(VaselineBusinessExecutorConfig.class)
public class VaselineFileBasicLayersConfig {

    @Bean
    IVaselineFileApi fileApi() {
        return new VaselineFileApiImpl();
    }

    @Bean
    IVaselineFileService fileService() {
        return new VaselineFileServiceImpl();
    }
}
