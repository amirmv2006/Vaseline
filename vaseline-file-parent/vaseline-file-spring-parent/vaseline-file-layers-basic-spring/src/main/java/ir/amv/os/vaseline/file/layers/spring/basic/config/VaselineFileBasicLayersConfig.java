package ir.amv.os.vaseline.file.layers.spring.basic.config;

import ir.amv.os.vaseline.file.apis.business.server.IVaselineFileApi;
import ir.amv.os.vaseline.file.apis.service.server.IVaselineFileService;
import ir.amv.os.vaseline.file.layers.spring.basic.server.api.VaselineFileApiImpl;
import ir.amv.os.vaseline.file.layers.spring.basic.server.service.VaselineFileServiceImpl;
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
