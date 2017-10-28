package ir.amv.os.vaseline.file.hibernate.spring.config;

import ir.amv.os.vaseline.file.apis.dao.jpa.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.apis.dao.jpa.server.dao.base.path.IVaselineFilePathDao;
import ir.amv.os.vaseline.file.hibernate.spring.server.dao.base.VaselineFileBlobHibernateDaoImpl;
import ir.amv.os.vaseline.file.hibernate.spring.server.dao.base.VaselineFilePathHibernateDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amir
 */
@Configuration
public class VaselineFileHibernateConfig {

    @Bean
    IVaselineFileBlobDao fileBlobDao() {
        return new VaselineFileBlobHibernateDaoImpl();
    }

    @Bean
    IVaselineFilePathDao filePathDao() {
        return new VaselineFilePathHibernateDaoImpl();
    }
}
