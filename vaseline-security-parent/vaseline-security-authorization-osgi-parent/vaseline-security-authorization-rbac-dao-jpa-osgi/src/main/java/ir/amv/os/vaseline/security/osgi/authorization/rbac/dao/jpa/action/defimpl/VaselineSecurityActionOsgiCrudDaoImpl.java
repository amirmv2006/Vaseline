package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.defimpl;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.IJpaCriteriaPrunerFunctionalInterface;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.jpa.action.IImplementedSecurityActionJpaDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.BaseOsgiCrudDaoImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.IVaselineSecurityActionDao;
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
        implements IImplementedSecurityActionJpaDao<SecurityActionEntity>,
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
