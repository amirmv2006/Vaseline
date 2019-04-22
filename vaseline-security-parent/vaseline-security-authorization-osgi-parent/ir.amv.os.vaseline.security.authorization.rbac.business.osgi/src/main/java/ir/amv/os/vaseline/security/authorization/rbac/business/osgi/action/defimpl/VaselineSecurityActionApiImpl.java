package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.action.defimpl;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.basic.def.server.action.function.IBusinessFunctionNoArg;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.def.action.IDefaultSecurityActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.BaseApiImpl;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.action.IVaselineSecurityActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.action.IVaselineSecurityActionDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.transaction.Transactional;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineSecurityActionApi.class,
                IProxyAware.class
        }
)
public class VaselineSecurityActionApiImpl
        extends BaseApiImpl<SecurityActionEntity, IVaselineSecurityActionDao>
        implements IDefaultSecurityActionApi<SecurityActionEntity, IVaselineSecurityActionDao>,
        IDefaultCrudApi<SecurityActionEntity, Long, IVaselineSecurityActionDao>,
        IVaselineSecurityActionApi{

    private IVaselineSecurityActionDao dao;

    @Override
    public IVaselineSecurityActionDao getDao() {
        return dao;
    }

    @Reference
    public void setDao(final IVaselineSecurityActionDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    @VaselineBuinessMetadata({
            VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY
    })
    public SecurityActionEntity getActionByTreeName(final String actionTreeName) throws BaseVaselineServerException {
        return doBusinessAction((IBusinessFunctionNoArg<SecurityActionEntity>) () -> {
            SecurityActionEntity findById = getDao().getByActionTreeName(actionTreeName);
            postGet(findById);
            return findById;
        });
    }
}
