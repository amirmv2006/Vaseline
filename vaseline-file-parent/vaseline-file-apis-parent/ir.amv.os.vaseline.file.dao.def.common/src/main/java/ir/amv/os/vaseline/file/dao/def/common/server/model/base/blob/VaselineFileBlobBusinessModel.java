package ir.amv.os.vaseline.file.dao.def.common.server.model.base.blob;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.model.BaseBusinessModelImpl;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileBusinessModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
@Entity
@Table(name =  "VASELINE_FILE_BLOB")
public class VaselineFileBlobBusinessModel extends BaseBusinessModelImpl<Long> implements IVaselineFileBusinessModel {

    private String fileName;
    private Long fileSize;
    private String owner;
    private String category;
    private String contentType;
    private Blob fileContent;
    private Date createDate;
    private Date modifyDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(updatable = false)
    public Blob getFileContent() {
        return fileContent;
    }

    public void setFileContent(Blob fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Long getFileSize() {
        return fileSize;
    }

    @Override
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getModifyDate() {
        return modifyDate;
    }

    @Override
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
