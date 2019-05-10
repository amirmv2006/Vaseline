package ir.amv.os.vaseline.security.authorization.rbac.business.osgi.action;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.authorization.business.api.IBaseNotSecuredCrudApi;
import ir.amv.os.vaseline.security.authorization.rbac.business.api.action.ISecurityActionApi;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.action.SecurityActionEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityActionApi
        extends
        ISecurityActionApi<SecurityActionEntity>,
        IBaseCrudApi<Long, SecurityActionEntity>,
        IBaseNotSecuredCrudApi<SecurityActionEntity, Long> {
    SecurityActionEntity getActionByTreeName(String actionTreeName) throws BaseVaselineServerException;
}
