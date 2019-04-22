package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IVendorSpecificDaoHelper {
    <E extends IBaseEntity<?>> IVaselineDataScroller<E> scrollQuery(final EntityManager em, TypedQuery<E> query);
}
