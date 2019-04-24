package ir.amv.os.vaseline.file.business.def;

import ir.amv.os.vaseline.file.business.api.IVaselineFileApi;
import ir.amv.os.vaseline.file.dao.def.hibernate.base.TestFileDaoHibernatePostgresqlConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TestFileDaoHibernatePostgresqlConfig.class})
public class TestVaselineFileApiSpringConfig {

    @Bean
    public IVaselineFileApi vaselineFileApi() {
        return new TestVaselineFileApiImpl();
    }
}
