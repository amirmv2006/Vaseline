package ir.amv.os.vaseline.basics.core.api.server.base.entity.hascreatedate;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseHasCreateDateEntity<Id extends Serializable>
        extends IBaseEntity<Id> {

    Date getCreateDate();
    void setCreateDate(Date createDate);
}
