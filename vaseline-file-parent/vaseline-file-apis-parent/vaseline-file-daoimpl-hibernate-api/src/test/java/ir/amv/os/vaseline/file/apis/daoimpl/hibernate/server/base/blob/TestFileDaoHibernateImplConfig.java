package ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.blob;

import ir.amv.os.vaseline.data.test.model.config.AbstractTestContainerBasedHibernateConfig;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.blob.IVaselineFileBlobDao;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQL94Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestFileDaoHibernateImplConfig extends AbstractTestContainerBasedHibernateConfig<PostgreSQLContainer> {

    @Bean
    public IVaselineFileBlobDao vaselineFileBlobDao() {
        return new TestFileBlobDao();
    }

    @Override
    protected PostgreSQLContainer createContainer() {
        return new PostgreSQLContainer();
    }

    @Override
    protected Class<? extends Dialect> getDialectClass() {
        return PostgreSQL94Dialect.class;
    }
}
