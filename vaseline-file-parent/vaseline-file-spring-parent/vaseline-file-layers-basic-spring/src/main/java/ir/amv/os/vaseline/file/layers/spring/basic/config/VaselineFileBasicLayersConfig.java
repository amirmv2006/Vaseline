package ir.amv.os.vaseline.file.layers.spring.basic.config;

import ir.amv.os.vaseline.file.business.api.IVaselineFileApi;
import ir.amv.os.vaseline.file.layers.spring.basic.server.api.VaselineFileApiImpl;
import ir.amv.os.vaseline.file.layers.spring.basic.server.service.VaselineFileServiceImpl;
import ir.amv.os.vaseline.file.service.api.IVaselineFileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
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
