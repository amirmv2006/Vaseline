package ir.amv.os.vaseline.file.api.server.model.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.hascreatedate.IBaseHasCreateDateEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.hasmodifydate.IBaseHasModifyDateEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileEntity extends IBaseHasCreateDateEntity<Long>, IBaseHasModifyDateEntity<Long> {

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
