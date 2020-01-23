package ir.amv.os.vaseline.basics.core.api.layers.business.model.hascreatedate;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseModel;

import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseHasCreateDateEntity<I extends IBaseModel<I>,
        M extends IBaseHasCreateDateEntity<I, M>>
        extends IBaseEntity<I, M> {

    Date getCreateDate();
    void setCreateDate(Date createDate);
}
