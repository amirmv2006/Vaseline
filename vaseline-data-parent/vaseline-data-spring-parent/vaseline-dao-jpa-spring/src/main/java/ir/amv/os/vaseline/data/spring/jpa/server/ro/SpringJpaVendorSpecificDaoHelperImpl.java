package ir.amv.os.vaseline.data.spring.jpa.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * @author Amir
 */
public class SpringJpaVendorSpecificDaoHelperImpl
        implements IVendorSpecificDaoHelper{
    @Override
    public <E extends IBaseEntity<?>> IVaselineDataScroller<E> scrollQuery(final EntityManager em, final TypedQuery<E> query) {
        Session session = em.unwrap(Session.class);
        Criteria unwrap = query.unwrap(Criteria.class);
        ScrollableResults scroll = unwrap.setReadOnly(true).scroll(ScrollMode.SCROLL_INSENSITIVE);
        return new DefaultHibernateDataScroller<>(scroll);
    }
}
