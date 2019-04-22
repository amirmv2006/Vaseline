package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.user.defimpl;

import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.def.user.IDefaultAuthorizationUserApi;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.user.IVaselineSecurityUserDao;
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
        implements IDefaultAuthorizationUserApi,
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
