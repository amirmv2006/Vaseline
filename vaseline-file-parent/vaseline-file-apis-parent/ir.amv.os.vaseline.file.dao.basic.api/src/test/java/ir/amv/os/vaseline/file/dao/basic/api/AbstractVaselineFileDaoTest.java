package ir.amv.os.vaseline.file.dao.basic.api;

import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileBusinessModel;
import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public abstract class AbstractVaselineFileDaoTest<E extends IVaselineFileBusinessModel, D extends IVaselineFileDto> {

    protected abstract IVaselineFileDao<E, D> getFileDao();
    protected abstract Resource getTestData();

    @Test
    public void testFileSaveAndRetrieve() throws Exception {
        String category = "Test";
        E file = getFileDao().createFile(category);
        file.setCategory(category);
        file.setContentType("text");
        file.setCreateDate(new Date());
        file.setFileName("test.data");
        file.setFileSize(getTestData().contentLength());
        file.setOwner("TestRunner");
        Long fileId = getFileDao().saveFileUsingStream(file, getTestData().getInputStream());
        E byId = getFileDao().getById(fileId);
        File tempFile = File.createTempFile("MyTest", null);
        getFileDao().writeFileContent(fileId, new FileOutputStream(tempFile));
        assertEquals(getTestData().contentLength(), byId.getFileSize().longValue());
        assertTrue(IOUtils.contentEquals(getTestData().getInputStream(), new FileInputStream(tempFile)));
    }
}
