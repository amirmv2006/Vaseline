package ir.amv.os.vaseline.data.spring.jpa.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
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
    public <E extends IBaseBusinessModel<?>> IVaselineDataScroller<E> scrollQuery(final EntityManager em, final TypedQuery<E> query) {
        Session session = em.unwrap(Session.class);
        Criteria unwrap = query.unwrap(Criteria.class);
        ScrollableResults scroll = unwrap.setReadOnly(true).scroll(ScrollMode.SCROLL_INSENSITIVE);
        return new DefaultHibernateDataScroller<>(scroll);
    }
}
