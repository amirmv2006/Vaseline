package ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.action.defimpl;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.IJpaCriteriaPrunerFunctionalInterface;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.authorization.rbac.dao.def.jpa.action.IDefaultSecurityActionJpaDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.BaseOsgiCrudDaoImpl;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.action.IVaselineSecurityActionDao;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineSecurityActionDao.class
)
public class VaselineSecurityActionOsgiCrudDaoImpl
        extends BaseOsgiCrudDaoImpl<SecurityActionEntity>
        implements IDefaultSecurityActionJpaDao<SecurityActionEntity>,
        IVaselineSecurityActionDao {

    @Override
    public SecurityActionEntity getByActionTreeName(final String actionTreeName) {
        return new JpaFetchProviderFacade<>(
                jpaFetchProvider(), this, (IJpaCriteriaPrunerFunctionalInterface<SecurityActionEntity>)
                (criteriaBuilder, query, fromProvider) -> applyRootCondition(
                        criteriaBuilder, fromProvider, query, criteriaBuilder.equal(
                                fromProvider.getCriteriaParentProjection("actionTreeName", null), actionTreeName)
        )).unique();
    }
}
