package ir.amv.os.vaseline.security.authentication.dao.jpa.osgi.defimpl;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud.IDefaultJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.dao.def.jpa.server.base.IDefaultBaseUserJpaDao;
import ir.amv.os.vaseline.security.authentication.model.def.server.base.VaselineInternalUserEntity;
import ir.amv.os.vaseline.security.authentication.dao.jpa.osgi.IVaselineInternalUserDao;
import org.osgi.service.component.annotations.Component;
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
@Component(
        immediate = true,
        service = {
                IVaselineInternalUserDao.class,
                IBaseUserDao.class
        }
)
public class VaselineInternalUserDaoJpa
        implements IVaselineInternalUserDao,
        IDefaultBaseUserJpaDao<VaselineInternalUserEntity>,
        IDefaultJpaCrudDao<Long, VaselineInternalUserEntity> {
    // list didn't work because remove will call equals which needs a tx :\
    private Map<String, EntityManager> emMap = new HashMap<>();
    private IVendorSpecificDaoHelper vendorSpecificDaoHelper;

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return vendorSpecificDaoHelper;
    }

    @Override
    public void setEntityClass(final Class<VaselineInternalUserEntity> entityClass) {
    }

    @Override
    public Class<? extends VaselineInternalUserEntity> getEntityClass() {
        return VaselineInternalUserEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        for (EntityManager entityManager : emMap.values()) {
            if (isBaseUserEM(entityManager)) {
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
    private boolean isBaseUserEM(final EntityManager em) {
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {
            if (entity.getJavaType().equals(VaselineInternalUserEntity.class)) {
                return true;
            }
        }
        return false;
    }
}
