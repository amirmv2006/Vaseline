package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.user;

import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.user.SecurityUserEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityUserDao
        extends ISecurityUserDao<SecurityUserEntity>, IBaseCrudDao<SecurityUserEntity, Long> {
}
