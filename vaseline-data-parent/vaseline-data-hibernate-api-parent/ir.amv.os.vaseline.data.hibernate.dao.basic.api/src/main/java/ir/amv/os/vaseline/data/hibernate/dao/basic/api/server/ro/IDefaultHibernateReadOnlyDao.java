package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.basics.jdbc.api.dialect.IVaselineJdbcDialect;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IDefaultGenericReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.base.IBaseHibernateDao;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor.DefaultHibernateFetchProviderImpl;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor.IHibernateFetchProvider;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.scroller.DefaultHibernateDataScroller;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Cacheable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

// note to future me: don't create the parent concrete class for these interfaces cause they will soon become
// useless, a better idea would be to put the parent classes in the child project itself based on the feature set
// that the child project is using
public interface IDefaultHibernateReadOnlyDao<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends IDefaultGenericReadOnlyDao<I, E>,
        IBaseReadOnlyDao<I, E>, IBaseHibernateDao {

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

    @Override
    default Long countAllApproximately() {
        Session currentSession = getCurrentSession();
        String query = getJdbcDialect().getCountAllApproximatelyQuery();
        SQLQuery sqlQuery = currentSession.createSQLQuery(query);
        String tableName = getEntityTableName();
        sqlQuery.setParameter("TABLE_NAME", tableName);
        Number o = (Number) sqlQuery.uniqueResult();
        return o == null ? 0 : o.longValue();
    }

    default String getEntityTableName() {
        // TODO read table name from metadata
        return getEntityClass().getAnnotation(Table.class).name();
    }

    default IVaselineJdbcDialect getJdbcDialect() {
        // TODO to be implemented
        return null;
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

    default IHibernateFetchProvider<I, E> hibernateFetchProvider() {
        return new DefaultHibernateFetchProviderImpl<>();
    }

    // Read Only Dao Methods
    default E getById(I id) {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(), this, detachedCriteria -> {
            detachedCriteria.add(Restrictions.idEq(id));
        }).unique();
    }

    default E getByIdDetached(I id) {
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

    default IVaselineDataScroller<E> scrollAll(List<SortDto> sortList) {
        return allHibernateCriteriaBuilderAbstractor().scroll(sortList);
    }

    default HibernateFetchProviderFacade<I, E> allHibernateCriteriaBuilderAbstractor() {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(), this, detachedCriteria -> {
        });
    }

}
