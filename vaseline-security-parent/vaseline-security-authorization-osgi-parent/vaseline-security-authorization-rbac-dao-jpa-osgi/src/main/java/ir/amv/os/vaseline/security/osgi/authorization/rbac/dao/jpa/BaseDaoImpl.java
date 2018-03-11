package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.crud.IBaseImplementedJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseImplementedJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Amir
 */
public class BaseDaoImpl<E extends IBaseEntity<Long>>
        implements IBaseImplementedJpaCrudDao<E, Long> {

    // list didn't work because remove will call equals which needs a tx :\
    private Map<String, EntityManager> emMap = new HashMap<>();
    private IVendorSpecificDaoHelper vendorSpecificDaoHelper;
    private Class<E> entityClass;

    public BaseDaoImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), BaseDaoImpl.class);
        if (genericArgumentClasses != null) {
            entityClass = (Class<E>) genericArgumentClasses[0];
        }
    }

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return vendorSpecificDaoHelper;
    }

    @Override
    public void setEntityClass(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    @Override
    public EntityManager getEntityManager() {
        for (EntityManager entityManager : emMap.values()) {
            if (isEntityEM(entityManager)) {
                return entityManager;
            }
        }
        return null;
    }

    @Reference(cardinality = ReferenceCardinality.OPTIONAL)
    public void setVendorSpecificDaoHelper(final IVendorSpecificDaoHelper vendorSpecificDaoHelper) {
        this.vendorSpecificDaoHelper = vendorSpecificDaoHelper;
    }

    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE,
            policyOption = ReferencePolicyOption.GREEDY
    )
    public void addEm(final EntityManager em) {
        emMap.put(UUID.randomUUID().toString(), em);
    }

    public void removeEm(final EntityManager em) {
        String keyToBeRemoved = null;
        for (Map.Entry<String, EntityManager> entry : emMap.entrySet()) {
            if (entry.getValue() == em) {
                keyToBeRemoved = entry.getKey();
            }
        }
        emMap.remove(keyToBeRemoved);
    }

    /**
     * checks if BaseUser is managed by the em
     * note: needs active transaction
     * @param em the em
     * @return whether BaseUser is managed by the em
     */
    private boolean isEntityEM(final EntityManager em) {
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {
            if (entity.getJavaType().equals(getEntityClass())) {
                return true;
            }
        }
        return false;
    }
}
