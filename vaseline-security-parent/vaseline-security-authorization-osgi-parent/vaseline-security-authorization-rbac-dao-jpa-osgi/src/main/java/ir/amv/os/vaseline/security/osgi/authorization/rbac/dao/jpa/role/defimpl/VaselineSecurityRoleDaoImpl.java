package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.role.defimpl;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.crud.IBaseImplementedJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.IJpaCriteriaPrunerFunctionalInterface;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role.SecurityRoleEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.BaseDaoImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.role.IVaselineSecurityRoleDao;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineSecurityRoleDao.class
)
public class VaselineSecurityRoleDaoImpl
        extends BaseDaoImpl<SecurityRoleEntity>
        implements
        IBaseImplementedJpaCrudDao<SecurityRoleEntity, Long>,
        IVaselineSecurityRoleDao {
    @Override
    public SecurityRoleEntity getByRoleName(final String roleName) {
        return new JpaFetchProviderFacade<>(
                jpaFetchProvider(),
                this, (IJpaCriteriaPrunerFunctionalInterface<SecurityRoleEntity>)
                (criteriaBuilder, query, fromProvider) ->
                        applyRootCondition(criteriaBuilder, fromProvider, query, criteriaBuilder.equal(
                                fromProvider.getCriteriaParentProjection("roleName", null), roleName)
                        )
        ).unique();
    }
}
