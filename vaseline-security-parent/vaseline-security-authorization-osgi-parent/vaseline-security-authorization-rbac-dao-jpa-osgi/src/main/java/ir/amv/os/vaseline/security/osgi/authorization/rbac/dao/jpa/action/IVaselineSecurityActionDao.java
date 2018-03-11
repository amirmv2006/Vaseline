package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action;

import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityActionDao
        extends ISecurityActionDao<SecurityActionEntity>, IBaseCrudDao<SecurityActionEntity, Long> {
    SecurityActionEntity getByActionTreeName(String actionTreeName);
}
