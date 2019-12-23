package ir.amv.os.vaseline.basics.core.api.layers.business.model.hasmodifydate;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseHasModifyDateBusinessModel<Id extends Serializable>
        extends IBaseBusinessModel<Id> {

    Date getModifyDate();
    void setModifyDate(Date modifyDate);
}
