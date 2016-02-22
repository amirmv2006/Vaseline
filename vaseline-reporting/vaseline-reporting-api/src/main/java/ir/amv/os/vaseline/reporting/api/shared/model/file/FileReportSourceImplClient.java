package ir.amv.os.vaseline.reporting.api.shared.model.file;

import ir.amv.os.vaseline.base.mapper.server.annot.VaselineMapTo;
import ir.amv.os.vaseline.reporting.api.server.model.file.FileReportSourceImplServer;
import ir.amv.os.vaseline.reporting.api.shared.model.IBaseReportSourceClient;

/**
 * Created by AMV on 2/17/2016.
 */
@VaselineMapTo(mapToServerClass = FileReportSourceImplServer.class)
public class FileReportSourceImplClient implements IBaseReportSourceClient {

    private Long fileId;
    private String fileCategory;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileCategory() {
        return fileCategory;
    }

    public void setFileCategory(String fileCategory) {
        this.fileCategory = fileCategory;
    }
}
