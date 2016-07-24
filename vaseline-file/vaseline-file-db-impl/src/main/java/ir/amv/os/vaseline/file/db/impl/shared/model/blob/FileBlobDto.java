package ir.amv.os.vaseline.file.db.impl.shared.model.blob;

import ir.amv.os.vaseline.base.core.shared.base.dto.base.impl.BaseDtoImpl;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;

import java.util.Date;

/**
 * Created by AMV on 7/9/2016.
 */
public class FileBlobDto
        extends BaseDtoImpl<Long>
        implements IFileDto {

    private String fileName;
    private Long fileSize;
    private String owner;
    private String category;
    private String contentType;
    private Date createDate;
    private Date modifyDate;

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
