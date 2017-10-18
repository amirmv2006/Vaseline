package ir.amv.os.vaseline.file.apis.model.shared;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

import java.util.Date;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileDto extends IBaseDto<Long> {

    String getFileName();

    void setFileName(String fileName);

    Long getFileSize();

    void setFileSize(Long fileSize);

    String getOwner();

    void setOwner(String owner);

    String getCategory();

    void setCategory(String category);

    String getContentType();

    void setContentType(String contentType);

    Date getCreateDate();

    void setCreateDate(Date createDate);

    Date getModifyDate();

    void setModifyDate(Date modifyDate);
}
