package ir.amv.os.vaseline.file.jpa.spring.basic.config;

import ir.amv.os.vaseline.file.apis.dao.jpa.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.apis.dao.jpa.server.dao.base.path.IVaselineFilePathDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
public class VaselineFileJpaConfig {

    @Bean
    IVaselineFileBlobDao fileBlobDao() {
//        return new FileBlobJpaDaoImpl();
        return null;
    }

    @Bean
    IVaselineFilePathDao filePathDao() {
//        return new FilePathJpaDaoImpl();
        return null;
    }
}
