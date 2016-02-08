package ir.amv.os.vaseline.file.api.server.model.base;

import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileEntity extends IBaseEntity<Long> {

    String getFileName();
    Long getFileSize();
    String getOwner();
    String getCategory();
    String getContentType();

}
