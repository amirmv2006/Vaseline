package ir.amv.os.vaseline.basics.core.api.layers.business.model.hasmodifydate;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseModel;

import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseHasModifyDateEntity<I extends IBaseModel<I>,
        M extends IBaseHasModifyDateEntity<I, M>>
        extends IBaseEntity<I, M> {

    Date getModifyDate();
    void setModifyDate(Date modifyDate);
}
