package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.role.defimpl;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.def.server.action.function.IBusinessFunctionNoArg;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.role.SecurityRoleEntity;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.BaseApiImpl;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.role.IVaselineSecurityRoleApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.role.IVaselineSecurityRoleDao;
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
        IDefaultCrudApi<SecurityRoleEntity, Long, IVaselineSecurityRoleDao>,
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
        return doBusinessAction((IBusinessFunctionNoArg<SecurityRoleEntity>) () -> {
            SecurityRoleEntity findById = getDao().getByRoleName(roleName);
            postGet(findById);
            return findById;
        });
    }
}
