package ir.amv.os.vaseline.file.apis.businessimpl.server;

import ir.amv.os.vaseline.business.spring.executor.config.VaselineBusinessExecutorConfig;
import ir.amv.os.vaseline.file.apis.business.server.IVaselineFileApi;
import ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.TestFileDaoHibernatePostgresqlConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TestFileDaoHibernatePostgresqlConfig.class, VaselineBusinessExecutorConfig.class})
public class TestVaselineFileApiSpringConfig {

    @Bean
    public IVaselineFileApi vaselineFileApi() {
        return new TestVaselineFileApiImpl();
    }
}
