package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.action.defimpl;

import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.def.action.IImplementedAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.action.IVaselineSecurityActionDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IAuthorizationActionApi.class
)
public class VaselineAuthorizationActionApiImpl
        implements IImplementedAuthorizationActionApi,
        IAuthorizationActionApi {
    private IVaselineSecurityActionDao dao;

    @Override
    public ISecurityActionDao<?> getDao() {
        return dao;
    }

    @Reference
    public void setDao(final IVaselineSecurityActionDao dao) {
        this.dao = dao;
    }
}
