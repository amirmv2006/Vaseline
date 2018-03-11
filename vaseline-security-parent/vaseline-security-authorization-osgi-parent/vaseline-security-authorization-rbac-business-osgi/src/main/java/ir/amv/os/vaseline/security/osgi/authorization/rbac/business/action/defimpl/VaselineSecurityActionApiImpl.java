package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.crud.IBaseImplementedCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.action.IImplementedSecurityActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.BaseApiImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.IVaselineSecurityActionApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.IVaselineSecurityActionDao;
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
        implements IImplementedSecurityActionApi<SecurityActionEntity, IVaselineSecurityActionDao>,
        IBaseImplementedCrudApi<SecurityActionEntity, Long, IVaselineSecurityActionDao>,
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
        return doBusinessAction((IBusinessFunctionZero<SecurityActionEntity>) () -> {
            SecurityActionEntity findById = getDao().getByActionTreeName(actionTreeName);
            postGet(findById);
            return findById;
        });
    }
}
