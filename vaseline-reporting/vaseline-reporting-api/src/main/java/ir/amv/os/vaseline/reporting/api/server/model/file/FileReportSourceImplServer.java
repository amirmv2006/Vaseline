package ir.amv.os.vaseline.reporting.api.server.model.file;

import ir.amv.os.vaseline.basics.apis.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.BaseInputStreamReportSourceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by AMV on 2/17/2016.
 */
public class FileReportSourceImplServer
        extends BaseInputStreamReportSourceImpl {

    private Long fileId;
    private String fileCategory;
    private IFileApi fileApi;

    @Override
    protected InputStream getReportDesignInputStream() throws BaseVaselineServerException {
        try {
            File tempFile = File.createTempFile(UUID.randomUUID()
                    .toString(), "jrxml");
            OutputStream outputStream = new FileOutputStream(tempFile);
            fileApi.writeFileContent(fileCategory, fileId, outputStream);
            outputStream.flush();
            outputStream.close();
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            return fileInputStream;
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public IFileApi getFileApi() {
        return fileApi;
    }

    public void setFileApi(IFileApi fileApi) {
        this.fileApi = fileApi;
    }

    public String getFileCategory() {
        return fileCategory;
    }

    public void setFileCategory(String fileCategory) {
        this.fileCategory = fileCategory;
    }
}
