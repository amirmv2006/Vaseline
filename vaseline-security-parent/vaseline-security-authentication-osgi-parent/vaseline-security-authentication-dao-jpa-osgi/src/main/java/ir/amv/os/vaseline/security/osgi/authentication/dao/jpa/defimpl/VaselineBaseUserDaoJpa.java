package ir.amv.os.vaseline.security.osgi.authentication.dao.jpa.defimpl;

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
import java.util.Set;

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
        implements IVaselineBaseUserDao, IImplementedBaseUserJpaDao<VaselineBaseUserEntity> {
    private EntityManager em;
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
        return em;
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
        if (isBaseUserEM(em)) {
            this.em = em;
        }
    }

    public void removeEm(final EntityManager em) {
        if (isBaseUserEM(em)) {
            this.em = null;
        }
    }

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
