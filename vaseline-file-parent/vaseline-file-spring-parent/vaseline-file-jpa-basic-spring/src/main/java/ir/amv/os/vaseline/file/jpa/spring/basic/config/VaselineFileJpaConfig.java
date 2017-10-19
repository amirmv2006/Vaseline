package ir.amv.os.vaseline.file.jpa.spring.basic.config;

import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.blob.IFileBlobDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.path.IFilePathDao;
import ir.amv.os.vaseline.file.jpa.spring.basic.server.dao.base.FileBlobJpaDaoImpl;
import ir.amv.os.vaseline.file.jpa.spring.basic.server.dao.base.FilePathJpaDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
public class VaselineFileJpaConfig {

    @Bean
    IFileBlobDao fileBlobDao() {
//        return new FileBlobJpaDaoImpl();
        return null;
    }

    @Bean
    IFilePathDao filePathDao() {
//        return new FilePathJpaDaoImpl();
        return null;
    }
}
