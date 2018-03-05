package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action.defimpl;

import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.action.IImplementedAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.IVaselineSecurityActionDao;
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
