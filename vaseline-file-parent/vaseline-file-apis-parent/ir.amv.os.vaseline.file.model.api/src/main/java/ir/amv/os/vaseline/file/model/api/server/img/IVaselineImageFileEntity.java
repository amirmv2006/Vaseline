package ir.amv.os.vaseline.file.model.api.server.img;

import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineImageFileEntity extends IVaselineFileEntity {
    Integer getImageWidth();
    Integer getImageHeight();
}