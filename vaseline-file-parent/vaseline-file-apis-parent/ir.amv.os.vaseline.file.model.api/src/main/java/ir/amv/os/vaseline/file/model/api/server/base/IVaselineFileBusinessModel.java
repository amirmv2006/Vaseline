package ir.amv.os.vaseline.file.model.api.server.base;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.hascreatedate.IBaseHasCreateDateBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.model.hasmodifydate.IBaseHasModifyDateBusinessModel;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineFileBusinessModel extends IBaseHasCreateDateBusinessModel<Long>, IBaseHasModifyDateBusinessModel<Long> {

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
