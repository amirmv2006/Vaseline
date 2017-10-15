package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.server.base.defimpl.BaseDaoImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public class BaseReadOnlyHibernateDaoImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseDaoImpl
        implements IBaseImplementedHibernateReadOnlyDao<E, Id> {

    private SessionFactory sessionFactory;
    private Class<E> entityClass;
    private String jdbcDriver;

    public BaseReadOnlyHibernateDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            setEntityClass((Class<E>)genericArgumentClasses[0]);
        }
    }

    @Override
    public Long countAllApproximately() {
        Session currentSession = getCurrentSession();
        String query = "";
        if (jdbcDriver.toLowerCase().contains("postgres")) {
            query = "SELECT reltuples AS approximate_row_count FROM pg_class WHERE relname = :REL_NAME";
        }
        SQLQuery sqlQuery = currentSession.createSQLQuery(query);
        String tableName = entityClass.getAnnotation(Table.class).name();
        sqlQuery.setParameter("REL_NAME", tableName);
        Number o = (Number) sqlQuery.uniqueResult();
        return o == null ? 0 : o.longValue();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
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
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
