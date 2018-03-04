package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.role.defimpl;

import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role.SecurityRoleEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.BaseDaoImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.role.IVaselineSecurityRoleDao;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineSecurityRoleDao.class
)
public class VaselineSecurityRoleDaoImpl
        extends BaseDaoImpl<SecurityRoleEntity>
        implements IVaselineSecurityRoleDao {
}
