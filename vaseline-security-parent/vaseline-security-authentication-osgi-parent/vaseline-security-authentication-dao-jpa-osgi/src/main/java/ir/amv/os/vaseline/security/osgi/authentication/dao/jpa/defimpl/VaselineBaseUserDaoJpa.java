package ir.amv.os.vaseline.security.osgi.authentication.dao.jpa.defimpl;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.crud.IBaseImplementedJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.apis.authentication.daoimpl.jpa.server.base.IImplementedBaseUserJpaDao;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineBaseUserEntity;
import ir.amv.os.vaseline.security.osgi.authentication.dao.jpa.IVaselineBaseUserDao;
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
                IVaselineBaseUserDao.class,
                IBaseUserDao.class
        }
)
public class VaselineBaseUserDaoJpa
        implements IVaselineBaseUserDao,
        IImplementedBaseUserJpaDao<VaselineBaseUserEntity>,
        IBaseImplementedJpaCrudDao<VaselineBaseUserEntity, Long> {
    // list didn't work because remove will call equals which needs a tx :\
    private Map<String, EntityManager> emMap = new HashMap<>();
    private IVendorSpecificDaoHelper vendorSpecificDaoHelper;

    @Override
    public IVendorSpecificDaoHelper getVendorSpecificDaoHelper() {
        return vendorSpecificDaoHelper;
    }

    @Override
    public void setEntityClass(final Class<VaselineBaseUserEntity> entityClass) {
    }

    @Override
    public Class<VaselineBaseUserEntity> getEntityClass() {
        return VaselineBaseUserEntity.class;
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
            if (entity.getJavaType().equals(VaselineBaseUserEntity.class)) {
                return true;
            }
        }
        return false;
    }
}
