package ir.amv.os.vaseline.security.osgi.authorization.rbac.business.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.business.layer.IBaseNotSecuredCrudApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.business.action.ISecurityActionApi;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;

/**
 * @author Amir
 */
public interface IVaselineSecurityActionApi
        extends
        ISecurityActionApi<SecurityActionEntity>,
        IBaseCrudApi<SecurityActionEntity, Long>,
        IBaseNotSecuredCrudApi<SecurityActionEntity, Long> {
    SecurityActionEntity getActionByTreeName(String actionTreeName) throws BaseVaselineServerException;
}
