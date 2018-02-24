package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;

import javax.persistence.TypedQuery;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IVendorSpecificDaoHelper {
    <E extends IBaseEntity<?>> IVaselineDataScroller<E> scrollQuery(TypedQuery<E> query);
}
