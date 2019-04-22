package ir.amv.os.vaseline.file.dao.def.hibernate.base.blob;

import ir.amv.os.vaseline.file.dao.basic.api.AbstractVaselineFileDaoTest;
import ir.amv.os.vaseline.file.dao.basic.api.IVaselineFileDao;
import ir.amv.os.vaseline.file.dao.def.common.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.dao.def.common.server.model.base.blob.VaselineFileBlobEntity;
import ir.amv.os.vaseline.file.dao.def.common.shared.model.blob.VaselineFileBlobDto;
import ir.amv.os.vaseline.file.dao.def.hibernate.base.TestFileDaoHibernatePostgresqlConfig;
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
public class IDefaultVaselineFileBlobHibernateDaoPostgresqlTest
        extends AbstractVaselineFileDaoTest<VaselineFileBlobEntity, VaselineFileBlobDto> {


    @Value("classpath:test.data")
    private Resource testData;

    @Inject
    IVaselineFileBlobDao fileBlobDao;

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
    public IVaselineFileDao<VaselineFileBlobEntity, VaselineFileBlobDto> getFileDao() {
        return fileBlobDao;
    }

    @Override
    public Resource getTestData() {
        return testData;
    }
}