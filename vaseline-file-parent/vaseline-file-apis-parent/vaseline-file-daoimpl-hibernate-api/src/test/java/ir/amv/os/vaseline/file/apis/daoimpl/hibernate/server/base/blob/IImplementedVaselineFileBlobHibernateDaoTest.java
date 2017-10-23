package ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.blob;

import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.model.base.blob.VaselineFileBlobEntity;
import org.apache.commons.io.IOUtils;
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
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestFileDaoHibernateImplConfig.class)
public class IImplementedVaselineFileBlobHibernateDaoTest {


    @Value("classpath:test.data")
    private Resource testData;

    @Inject
    IVaselineFileBlobDao fileBlobDao;

    @BeforeClass
    public static void checkIfDockerEnv() {
        Assume.assumeTrue(System.getProperty("docker.available").equals("true"));
    }

    @Test
    @Transactional
    public void testSaveFile() throws Exception {
        VaselineFileBlobEntity file = new VaselineFileBlobEntity();
        file.setCategory("Test");
        file.setContentType("text");
        file.setCreateDate(new Date());
        file.setFileName("test.data");
        file.setFileSize(testData.contentLength());
        file.setOwner("TestRunner");
        Long fileId = fileBlobDao.saveFileUsingStream(file, testData.getInputStream());
        VaselineFileBlobEntity byId = fileBlobDao.getById(fileId);
        Blob fileContent = byId.getFileContent();
        InputStream binaryStream = fileContent.getBinaryStream();
        assertEquals(testData.contentLength(), byId.getFileSize().longValue());
        assertTrue(IOUtils.contentEquals(binaryStream, testData.getInputStream()));
    }

}