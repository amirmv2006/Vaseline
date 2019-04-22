package ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.user.defimpl;

import ir.amv.os.vaseline.security.authentication.dao.def.jpa.server.base.IDefaultBaseUserJpaDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.def.jpa.user.IDefaultSecurityUserJpaDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.user.SecurityUserEntity;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.BaseOsgiCrudDaoImpl;
import ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.user.IVaselineSecurityUserDao;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineSecurityUserDao.class
)
public class VaselineSecurityUserOsgiCrudDaoImpl
        extends BaseOsgiCrudDaoImpl<SecurityUserEntity>
        implements IDefaultSecurityUserJpaDao<SecurityUserEntity>, IDefaultBaseUserJpaDao<SecurityUserEntity>,
        IVaselineSecurityUserDao {
}
