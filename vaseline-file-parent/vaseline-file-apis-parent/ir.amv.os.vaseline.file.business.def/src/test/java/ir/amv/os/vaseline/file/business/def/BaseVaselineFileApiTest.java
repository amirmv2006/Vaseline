package ir.amv.os.vaseline.file.business.def;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.file.business.api.IVaselineFileApi;
import ir.amv.os.vaseline.file.dao.def.common.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.dao.def.common.server.dao.base.path.IVaselineFilePathDao;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public abstract class BaseVaselineFileApiTest {

    @Value("classpath:blob.data")
    private Resource blobData;
    @Value("classpath:path.data")
    private Resource pathData;
    @Value("classpath:write.data")
    private Resource writeData;

    @Inject
    private IVaselineFileApi vaselineFileApi;
    @Inject
    private IVaselineFileBlobDao blobDao;
    @Inject
    private IVaselineFilePathDao pathDao;

    private Long writeDataFileId;

    @Before
    @Transactional
    public void before() throws Exception {
        writeDataFileId = vaselineFileApi.uploadFile("write.data", "blob", writeData.contentLength(), "text", writeData.getInputStream());
    }

    @Test
    @Transactional
    public void testUploadFile() throws Exception {
        Long fileId = testSaveFile("blob.data", "blob", blobData);
        File tempFile = File.createTempFile("TestFileApiBlob", null);
        blobDao.writeFileContent(fileId, new FileOutputStream(tempFile));
        assertEquals(blobData.contentLength(), tempFile.length());
        assertTrue(IOUtils.contentEquals(new FileInputStream(tempFile), blobData.getInputStream()));
        fileId = testSaveFile("path.data", "path", pathData);
        tempFile = File.createTempFile("TestFileApiPath", null);
        pathDao.writeFileContent(fileId, new FileOutputStream(tempFile));
        assertEquals(pathData.contentLength(), tempFile.length());
        assertTrue(IOUtils.contentEquals(new FileInputStream(tempFile), pathData.getInputStream()));
    }

    @Test
    @Transactional
    public void testWriteFileContent() throws Exception {
        File tempFile = File.createTempFile("TestFileApiWrite", null);
        vaselineFileApi.writeFileContent("blob", writeDataFileId, new FileOutputStream(tempFile));
        assertEquals(writeData.contentLength(), tempFile.length());
        assertTrue(IOUtils.contentEquals(new FileInputStream(tempFile), writeData.getInputStream()));
    }

    private Long testSaveFile(String testFileName, String testFileCategory, Resource resource) throws IOException, BaseBusinessException {
        Long testFileSize = resource.contentLength();
        String testContentType = "text";
        InputStream testInputStream = resource.getInputStream();
        return vaselineFileApi.uploadFile(testFileName, testFileCategory, testFileSize, testContentType, testInputStream);
    }
}
