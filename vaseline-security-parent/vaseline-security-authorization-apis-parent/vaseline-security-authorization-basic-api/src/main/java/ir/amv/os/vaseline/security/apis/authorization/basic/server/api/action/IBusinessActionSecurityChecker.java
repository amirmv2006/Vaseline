package ir.amv.os.vaseline.security.apis.authorization.basic.server.api.action;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction; /**
 * @author Amir
 */
public interface IBusinessActionSecurityChecker {
    <R> boolean isSecured(IBusinessAction<R> businessAction);

    <R> String getActionTreeName(IBusinessAction<R> businessAction);
}
