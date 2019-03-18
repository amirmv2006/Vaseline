package ir.amv.os.vaseline.file.model.api.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.hascreatedate.IBaseHasCreateDateEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.entity.hasmodifydate.IBaseHasModifyDateEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineFileEntity extends IBaseHasCreateDateEntity<Long>, IBaseHasModifyDateEntity<Long> {

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
}
