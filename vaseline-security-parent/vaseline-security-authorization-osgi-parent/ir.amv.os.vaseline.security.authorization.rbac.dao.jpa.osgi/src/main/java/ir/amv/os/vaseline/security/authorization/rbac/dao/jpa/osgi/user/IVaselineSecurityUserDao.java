package ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.user;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.user.SecurityUserEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityUserDao
        extends ISecurityUserDao<SecurityUserEntity>, IBaseCrudDao<Long, SecurityUserEntity> {
}
