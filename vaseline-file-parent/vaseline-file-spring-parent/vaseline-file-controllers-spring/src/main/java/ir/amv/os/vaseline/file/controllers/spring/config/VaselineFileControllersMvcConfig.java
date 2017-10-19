package ir.amv.os.vaseline.file.controllers.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author Amir
 */
@Configuration
@ComponentScan("ir.amv.os.vaseline.file.controllers.spring.server")
public class VaselineFileControllersMvcConfig {

    @Value("${vaseline.file.max.upload.size")
    private Long maxUploadSize;

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(maxUploadSize);
        return commonsMultipartResolver;
    }

}
