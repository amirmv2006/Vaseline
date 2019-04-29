package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.action.defimpl;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyAware;
import ir.amv.os.vaseline.business.basic.def.server.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.def.action.IDefaultSecurityActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.BaseApiImpl;
import ir.amv.os.vaseline.security.authorization.rbac.business.osgi.action.IVaselineSecurityActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.action.IVaselineSecurityActionDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.action.SecurityActionEntity;
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
    public SecurityActionEntity getActionByTreeName(final String actionTreeName) throws BaseVaselineServerException {
        SecurityActionEntity findById = getDao().getByActionTreeName(actionTreeName);
        postGet(findById);
        return findById;
    }
}
