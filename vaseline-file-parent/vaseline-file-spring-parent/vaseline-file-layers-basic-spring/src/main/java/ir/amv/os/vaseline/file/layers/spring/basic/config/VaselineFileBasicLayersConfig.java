package ir.amv.os.vaseline.file.layers.spring.basic.config;

import ir.amv.os.vaseline.file.apis.business.server.IFileApi;
import ir.amv.os.vaseline.file.apis.service.server.IFileService;
import ir.amv.os.vaseline.file.layers.spring.basic.server.api.FileApiImpl;
import ir.amv.os.vaseline.file.layers.spring.basic.server.service.FileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
public class VaselineFileBasicLayersConfig {

    @Bean
    IFileApi fileApi() {
        return new FileApiImpl();
    }

    @Bean
    IFileService fileService() {
        return new FileServiceImpl();
    }
}
