package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IVendorSpecificDaoHelper {
    <E extends IBaseBusinessModel<?>> IVaselineDataScroller<E> scrollQuery(final EntityManager em, TypedQuery<E> query);
}
