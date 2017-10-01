package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.projection.IJpaCriteriaFromProvider;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IVendorSpecificDaoHelper {
    <E extends IBaseEntity<?>> IVaselineDataScroller<E> scrollQuery(TypedQuery<E> query);
}
