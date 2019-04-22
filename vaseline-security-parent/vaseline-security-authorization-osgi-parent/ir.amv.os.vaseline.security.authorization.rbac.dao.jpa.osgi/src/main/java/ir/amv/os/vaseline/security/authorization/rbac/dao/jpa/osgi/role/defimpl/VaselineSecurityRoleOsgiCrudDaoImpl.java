package ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.role.defimpl;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud.IDefaultJpaCrudDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.IJpaCriteriaPrunerFunctionalInterface;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.role.SecurityRoleEntity;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.BaseOsgiCrudDaoImpl;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.role.IVaselineSecurityRoleDao;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineSecurityRoleDao.class
)
public class VaselineSecurityRoleOsgiCrudDaoImpl
        extends BaseOsgiCrudDaoImpl<SecurityRoleEntity>
        implements
        IDefaultJpaCrudDao<SecurityRoleEntity, Long>,
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
