package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.jdbc.dialect.IVaselineJdbcDialect;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.base.IBaseHibernateDao;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.DefaultHibernateFetchProviderImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.IHibernateFetchProvider;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.scroller.DefaultHibernateDataScroller;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
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
public interface IBaseImplementedHibernateReadOnlyDao<E extends IBaseEntity<Id>, Id extends Serializable>
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

    default IVaselineDataScroller<E> scrollAll(List<SortDto> sortList) {
        return allHibernateCriteriaBuilderAbstractor().scroll(sortList);
    }

    default HibernateFetchProviderFacade<E, Id> allHibernateCriteriaBuilderAbstractor() {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(), this, detachedCriteria -> {
        });
    }

    @Override
    default Class<E> getEntityClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClassesDeprecated(getClass(), IBaseReadOnlyDao.class);
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseEntity.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<E>) genericArgumentClass;
                }
            }
        }
        return null;
    }

    @Override
    default void setEntityClass(Class<E> entityClass) {
        // no need for this, I'm finding the generics on my own :)
    }
}
