package ir.amv.os.vaseline.file.db.impl.server.model.blob;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.ent.BaseEntityImpl;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by AMV on 2/9/2016.
 */
@Entity
@Table(name =  "FILE_BLOB")
public class FileBlobEntity extends BaseEntityImpl<Long> implements IFileEntity {

    private Blob fileContent;
    private String fileName;
    private Long fileSize;
    private String owner;
    private String category;
    private String contentType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
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

}
