package ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.path;

import ir.amv.os.vaseline.file.apis.dao.server.AbstractVaselineFileDaoTest;
import ir.amv.os.vaseline.file.apis.dao.server.IVaselineFileDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.path.IVaselineFilePathDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.model.base.path.VaselineFilePathEntity;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.shared.model.path.VaselineFilePathDto;
import ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.TestFileDaoHibernatePostgresqlConfig;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestFileDaoHibernatePostgresqlConfig.class)
public class IImplementedVaselineFilePathHibernateDaoPostgresqlTest
        extends AbstractVaselineFileDaoTest<VaselineFilePathEntity, VaselineFilePathDto> {

    @Value("classpath:test.data")
    private Resource testData;

    @Inject
    IVaselineFilePathDao filePathDao;

    @BeforeClass
    public static void checkIfDockerEnv() {
        String property = System.getProperty("docker.available");
        Assume.assumeTrue(property == null || property.equals("true"));
    }

    @Override
    @Test
    @Transactional
    public void testFileSaveAndRetrieve() throws Exception {
        super.testFileSaveAndRetrieve();
    }

    @Override
    public IVaselineFileDao<VaselineFilePathEntity, VaselineFilePathDto> getFileDao() {
        return filePathDao;
    }

    @Override
    public Resource getTestData() {
        return testData;
    }
}