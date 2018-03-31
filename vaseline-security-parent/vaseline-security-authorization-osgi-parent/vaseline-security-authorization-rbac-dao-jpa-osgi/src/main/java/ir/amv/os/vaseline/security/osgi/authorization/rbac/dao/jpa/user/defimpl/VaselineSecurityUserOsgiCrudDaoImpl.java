package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.user.defimpl;

import ir.amv.os.vaseline.security.apis.authentication.daoimpl.jpa.server.base.IImplementedBaseUserJpaDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.jpa.user.IImplementedSecurityUserJpaDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.user.SecurityUserEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.BaseOsgiCrudDaoImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.user.IVaselineSecurityUserDao;
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
        implements IImplementedSecurityUserJpaDao<SecurityUserEntity>, IImplementedBaseUserJpaDao<SecurityUserEntity>,
        IVaselineSecurityUserDao {
}
