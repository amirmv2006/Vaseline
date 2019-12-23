package ir.amv.os.vaseline.file.model.api.server.img;

import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileBusinessModel;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineImageFileBusinessModel extends IVaselineFileBusinessModel {
    Integer getImageWidth();
    Integer getImageHeight();
}
