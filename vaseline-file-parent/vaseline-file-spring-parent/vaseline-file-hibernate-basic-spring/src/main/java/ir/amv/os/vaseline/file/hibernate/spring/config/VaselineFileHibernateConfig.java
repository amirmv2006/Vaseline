package ir.amv.os.vaseline.file.hibernate.spring.config;

import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.blob.IFileBlobDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.path.IFilePathDao;
import ir.amv.os.vaseline.file.hibernate.spring.server.dao.base.FileBlobHibernateDaoImpl;
import ir.amv.os.vaseline.file.hibernate.spring.server.dao.base.FilePathHibernateDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
public class VaselineFileHibernateConfig {

    @Bean
    IFileBlobDao fileBlobDao() {
        return new FileBlobHibernateDaoImpl();
    }

    @Bean
    IFilePathDao filePathDao() {
        return new FilePathHibernateDaoImpl();
    }
}
