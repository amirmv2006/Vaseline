package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.user.defimpl;

import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.businessimpl.user.IImplementedAuthorizationUserApi;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.user.IVaselineSecurityUserDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * to fix the loop
 * @author Amir
 */
@Component(
        immediate = true,
        service = IAuthorizationUserApi.class
)
public class VaselineAuthorizationUserApiImpl
        implements IImplementedAuthorizationUserApi,
        IAuthorizationUserApi {

    private IVaselineSecurityUserDao dao;

    @Override
    public IVaselineSecurityUserDao getDao() {
        return dao;
    }

    @Reference
    public void setDao(final IVaselineSecurityUserDao dao) {
        this.dao = dao;
    }

}
