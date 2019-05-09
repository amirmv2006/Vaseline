package ir.amv.os.vaseline.security.authorization.rbac.dao.jpa.osgi.action;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.action.SecurityActionEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityActionDao
        extends ISecurityActionDao<SecurityActionEntity>, IBaseCrudDao<Long, SecurityActionEntity> {
    SecurityActionEntity getByActionTreeName(String actionTreeName);
}
