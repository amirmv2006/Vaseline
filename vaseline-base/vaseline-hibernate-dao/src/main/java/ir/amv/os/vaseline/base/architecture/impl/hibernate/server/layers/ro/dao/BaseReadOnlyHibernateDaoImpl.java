package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao.scroller.DefaultHibernateDataScroller;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.basics.apis.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.basics.apis.core.api.shared.util.callback.IBaseReturningCallback;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Cacheable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public class BaseReadOnlyHibernateDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends ExampleToCriteriaDaoImpl<D>
        implements IBaseReadOnlyDao<E, D, Id> {

    protected SessionFactory sessionFactory;
    private Class<E> entityClass;
    protected IPagingResultCreator pagingResultCreator;
    @Value("#{environment['hibernate.connection.driver_class']}")
    protected String jdbcDriver;

    public BaseReadOnlyHibernateDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            setEntityClass((Class<E>)genericArgumentClasses[0]);
        }
    }

    @Override
    public E getById(Id id) {
        DetachedCriteria detCriteria = createCriteria();
        detCriteria.add(Restrictions.idEq(id));
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        E uniqueResult = getEntityFromCriteria(criteria);
        return uniqueResult;
    }

    @Override
    public E getByIdDetached(Id id) {
        E byId = getById(id);
        Session currentSession = getCurrentSession();
        currentSession.evict(byId);
        return byId;
    }

    @Override
    public Long countAllApproximately() {
        Session currentSession = getCurrentSession();
        String query = "";
        if (jdbcDriver.toLowerCase().contains("postgres")) {
            query = "SELECT reltuples AS approximate_row_count FROM pg_class WHERE relname = ?";
        }
        SQLQuery sqlQuery = currentSession.createSQLQuery(query);
        String tableName = entityClass.getAnnotation(Table.class).name();
        sqlQuery.setString(0, tableName);
        Number o = (Number) sqlQuery.uniqueResult();
        return o == null ? 0 : o.longValue();
    }

    @Override
    public Long countAll() {
        DetachedCriteria detCriteria = createCriteria();
        detCriteria = getCountCriteria(detCriteria);
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return getCountResult(criteria);
    }

    @Override
    public List<E> getAll() {
        DetachedCriteria detCriteria = createCriteria();
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return getListFromCriteria(criteria);
    }

    @Override
    public IVaselineDataScroller<E> scrollAll() {
        DetachedCriteria detCriteria = createCriteria();
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return scrollCriteria(criteria);
    }

    @Override
    public List<E> getAll(PagingDto pagingDto) {
        List<E> listFromCriteria = pagingResultCreator.getPagingResult(this, new IBaseReturningCallback<DetachedCriteria>() {
            @Override
            public DetachedCriteria execute() {
                return createCriteria();
            }
        }, pagingDto);
        return listFromCriteria;
    }

    @Override
    public Long countByExample(D example) {
        DetachedCriteria detCriteria = createCriteria();
        pruneCriteriaBasedOnExampleRecursively(example, detCriteria,
                new ArrayList<Criterion>(), new HashMap<String, String>());
        detCriteria = getCountCriteria(detCriteria);
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        Long countResult = getCountResult(criteria);
        return countResult;
    }

    @Override
    public List<E> searchByExample(D example) {
        DetachedCriteria detachedCriteria = createCriteria();
        pruneCriteriaBasedOnExampleRecursively(example, detachedCriteria,
                new ArrayList<Criterion>(), new HashMap<String, String>());
        Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);
        List<E> listFromCriteria = getListFromCriteria(criteria);
        return listFromCriteria;
    }

    @Override
    public IVaselineDataScroller<E> scrollByExample(D example) {
        DetachedCriteria detCriteria = createCriteria();
        pruneCriteriaBasedOnExampleRecursively(example, detCriteria,
                new ArrayList<Criterion>(), new HashMap<String, String>());
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return scrollCriteria(criteria);
    }

    @Override
    public List<E> searchByExample(final D example, PagingDto pagingDto) {
        List<E> listFromCriteria = pagingResultCreator.getPagingResult(this, new IBaseReturningCallback<DetachedCriteria>() {
            @Override
            public DetachedCriteria execute() {
                DetachedCriteria detachedCriteria = createCriteria();
                pruneCriteriaBasedOnExampleRecursively(example, detachedCriteria,
                        new ArrayList<Criterion>(), new HashMap<String, String>());
                return detachedCriteria;
            }
        }, pagingDto);
        return listFromCriteria;
    }

    // BASE METHODS
    protected DetachedCriteria createCriteria() {
        DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
        return criteria;
    }

    public Criteria getCriteriaFromDetachedCriteria(
            DetachedCriteria detCriteria) {
        Session currentSession = getCurrentSession();
        Criteria criteria = detCriteria.getExecutableCriteria(currentSession);
        if (cacheAllCriterias()) {
            criteria.setCacheable(true);
        }
        return criteria;
    }

    protected boolean cacheAllCriterias() {
        return getEntityClass().isAnnotationPresent(Cacheable.class);
    }

    public Criteria paginateCriteria(Criteria criteria,
                                        final PagingDto paginationObject) {
        List<SortDto> sortList = paginationObject.getSortList();
        if (sortList == null || sortList.isEmpty()) {
            criteria.addOrder(Order.desc("id"));
        } else {
            SortDto sortDTO = sortList.get(0);
            criteria.addOrder(sortDTO.getAscending() ? Order.asc(sortDTO
                    .getPropertyName()) : Order.desc(sortDTO.getPropertyName()));
        }
        if (paginationObject.getPageNumber() != null && paginationObject.getPageSize() != null) {
            return criteria.setMaxResults(paginationObject.getPageSize())
                    .setFirstResult(
                            paginationObject.getPageNumber()
                                    * paginationObject.getPageSize());
        } else {
            return criteria;
        }
    }

    public Long getCountResult(Criteria criteria) {
        Number uniqueResult = (Number) criteria.uniqueResult();
        if (uniqueResult == null) {
            return 0L;
        }
        return uniqueResult.longValue();
    }

    public E getEntityFromCriteria(Criteria criteria) {
        Object uniqueResult = criteria.uniqueResult();
        return (E) uniqueResult;
    }

    public List<E> getListFromCriteria(Criteria criteria) {
        criteria = criteria
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<?> list = criteria.list();
        return (List<E>) list;
    }

    public IVaselineDataScroller scrollCriteria(Criteria criteria) {
        ScrollableResults scroll = criteria.setReadOnly(true).scroll(ScrollMode.SCROLL_INSENSITIVE);
        return new DefaultHibernateDataScroller(scroll);
    }

    public DetachedCriteria getCountCriteria(DetachedCriteria detCriteria) {
        return detCriteria.setProjection(Projections.rowCount());
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    // Spring Dependencies
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setPagingResultCreator(IPagingResultCreator pagingResultCreator) {
        this.pagingResultCreator = pagingResultCreator;
    }
}
