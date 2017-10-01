package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.server.base.defimpl.BaseDaoImpl;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by AMV on 10/2/2017.
 */
public class BaseReadOnlyJpaDaoImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseDaoImpl
        implements IBaseJpaReadOnlyDao<E, Id> {

    protected Class<E> entityClass;
    protected EntityManager em;
    protected String jdbcDriver;
    protected IVendorSpecificDaoHelper vendorSpecificDaoHelper;

    public BaseReadOnlyJpaDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            setEntityClass((Class<E>) genericArgumentClasses[0]);
        }
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return vendorSpecificDaoHelper;
    }

    @Override
    public Long countAllApproximately() {
        String query = "";
        if (jdbcDriver.toLowerCase().contains("postgres")) {
            query = "SELECT reltuples AS approximate_row_count FROM pg_class WHERE relname = :TABLE_NAME?";
        }
        Query sqlQuery = em.createNativeQuery(query);
        String tableName = entityClass.getAnnotation(Table.class).name();
        sqlQuery.setParameter("TABLE_NAME", tableName);
        Number o = (Number) sqlQuery.getSingleResult();
        return o == null ? 0 : o.longValue();
    }

    @Override
    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
