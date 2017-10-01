package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.base.IBaseHibernateDao;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.DefaultHibernateFetchProviderImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.IHibernateFetchProvider;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.scroller.DefaultHibernateDataScroller;
import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Cacheable;
import java.io.Serializable;
import java.util.List;

public interface IBaseHibernateReadOnlyDao<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseReadOnlyDao<E, Id>, IBaseHibernateDao {

    default boolean cacheAllCriterias() {
        return getEntityClass().isAnnotationPresent(Cacheable.class);
    }

    default DetachedCriteria createCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }

    default Criteria getCriteriaFromDetachedCriteria(
            DetachedCriteria detCriteria) {
        Session currentSession = getCurrentSession();
        Criteria criteria = detCriteria.getExecutableCriteria(currentSession);
        if (cacheAllCriterias()) {
            criteria.setCacheable(true);
        }
        return criteria;
    }

    default E getEntityFromCriteria(Criteria criteria) {
        Object uniqueResult = criteria.uniqueResult();
        return (E) uniqueResult;
    }

    default List<E> getListFromCriteria(Criteria criteria) {
        criteria = criteria
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<?> list = criteria.list();
        return (List<E>) list;
    }

    default IVaselineDataScroller<E> scrollCriteria(Criteria criteria) {
        ScrollableResults scroll = criteria.setReadOnly(true).scroll(ScrollMode.SCROLL_INSENSITIVE);
        return new DefaultHibernateDataScroller<>(scroll);
    }

    default IHibernateFetchProvider<E, Id> hibernateFetchProvider() {
        return new DefaultHibernateFetchProviderImpl<>();
    }

    // Read Only Dao Methods
    default E getById(Id id) {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(), this, detachedCriteria -> {
            detachedCriteria.add(Restrictions.idEq(id));
        }).unique();
    }

    default E getByIdDetached(Id id) {
        E byId = getById(id);
        Session currentSession = getCurrentSession();
        currentSession.evict(byId);
        return byId;
    }

    default Long countAll() {
        return allHibernateCriteriaBuilderAbstractor().count();
    }

    default List<E> getAll() {
        return allHibernateCriteriaBuilderAbstractor().list();
    }

    default List<E> getAll(PagingDto pagingDto) {
        return allHibernateCriteriaBuilderAbstractor().page(pagingDto);
    }

    default IVaselineDataScroller<E> scrollAll() {
        return allHibernateCriteriaBuilderAbstractor().scroll();
    }

    default HibernateFetchProviderFacade<E, Id> allHibernateCriteriaBuilderAbstractor() {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(), this, detachedCriteria -> {
        });
    }

}
