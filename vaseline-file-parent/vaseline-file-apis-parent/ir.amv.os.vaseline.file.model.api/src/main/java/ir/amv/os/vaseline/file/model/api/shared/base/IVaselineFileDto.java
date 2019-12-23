package ir.amv.os.vaseline.file.model.api.shared.base;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;

import java.util.Date;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineFileDto extends IBaseDto<Long> {

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
