package ir.amv.os.vaseline.base.core.api.server.base.ent.hascreatedate;

import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;

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
