package ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base;

import ir.amv.os.vaseline.data.test.model.config.AbstractTestContainerBasedHibernateConfig;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.path.IVaselineFilePathDao;
import ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.blob.TestFileBlobDao;
import ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.path.TestFilePathDao;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQL94Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestFileDaoHibernatePostgresqlConfig extends AbstractTestContainerBasedHibernateConfig<PostgreSQLContainer> {

    @Bean
    public IVaselineFileBlobDao vaselineFileBlobDao() {
        return new TestFileBlobDao();
    }

    @Bean
    public IVaselineFilePathDao vaselineFilePathDao() {
        return new TestFilePathDao(System.getProperty("java.io.tmpdir"));
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
