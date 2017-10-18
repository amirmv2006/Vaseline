package ir.amv.os.vaseline.file.apis.model.server.img;

import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IImageFileEntity extends IFileEntity {
    Integer getImageWidth();
    Integer getImageHeight();
}
