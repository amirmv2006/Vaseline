package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.role.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role.SecurityRoleEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.BaseApiImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.role.IVaselineSecurityRoleApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.role.IVaselineSecurityRoleDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.transaction.Transactional;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineSecurityRoleApi.class,
                IProxyAware.class
        }
)
public class VaselineSecurityRoleApiImpl
        extends BaseApiImpl<SecurityRoleEntity, IVaselineSecurityRoleDao>
        implements
        IBaseImplementedCrudApi<SecurityRoleEntity, Long, IVaselineSecurityRoleDao>,
        IVaselineSecurityRoleApi {

    private IVaselineSecurityRoleDao dao;

    @Override
    public IVaselineSecurityRoleDao getDao() {
        return dao;
    }

    @Reference
    public void setDao(final IVaselineSecurityRoleDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    public SecurityRoleEntity getRoleByName(final String roleName) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionZero<SecurityRoleEntity>) () -> {
            SecurityRoleEntity findById = getDao().getByRoleName(roleName);
            postGet(findById);
            return findById;
        });
    }
}
