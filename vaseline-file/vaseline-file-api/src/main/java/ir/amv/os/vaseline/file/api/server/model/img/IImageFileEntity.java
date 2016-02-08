package ir.amv.os.vaseline.file.api.server.model.img;

import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IImageFileEntity extends IFileEntity {
    Integer getImageWidth();
    Integer getImageHeight();
}
