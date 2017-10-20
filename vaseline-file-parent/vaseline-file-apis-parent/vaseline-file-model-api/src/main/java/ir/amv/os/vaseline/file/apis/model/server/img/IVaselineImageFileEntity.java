package ir.amv.os.vaseline.file.apis.model.server.img;

import ir.amv.os.vaseline.file.apis.model.server.base.IVaselineFileEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineImageFileEntity extends IVaselineFileEntity {
    Integer getImageWidth();
    Integer getImageHeight();
}
