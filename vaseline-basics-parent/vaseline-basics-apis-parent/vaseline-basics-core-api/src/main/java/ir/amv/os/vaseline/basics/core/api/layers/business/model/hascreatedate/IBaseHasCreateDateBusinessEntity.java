package ir.amv.os.vaseline.basics.core.api.layers.business.model.hascreatedate;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseHasCreateDateBusinessEntity<I extends IBaseBusinessModel<I>,
        M extends IBaseHasCreateDateBusinessEntity<I, M>>
        extends IBaseBusinessEntity<I, M> {

    Date getCreateDate();
    void setCreateDate(Date createDate);
}
