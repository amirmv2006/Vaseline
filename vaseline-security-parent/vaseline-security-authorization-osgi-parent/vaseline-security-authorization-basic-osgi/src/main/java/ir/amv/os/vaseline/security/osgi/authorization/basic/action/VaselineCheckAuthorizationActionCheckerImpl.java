package ir.amv.os.vaseline.security.osgi.authorization.basic.action;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.action.IBusinessActionSecurityChecker;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.annot.ActionTreeName;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.annot.CheckAuthorization;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IBusinessActionSecurityChecker.class
)
public class VaselineCheckAuthorizationActionCheckerImpl
        implements IBusinessActionSecurityChecker {
    @Override
    public <R> boolean isSecured(final IBusinessAction<R> businessAction) {
        CheckAuthorization checkAuthorization = ReflectionUtil.getMethodAnnotationInHierarchy(CheckAuthorization.class,
                businessAction.getRunningClass(), businessAction.getDeclaredMethod().getName(),
                businessAction.getDeclaredMethod().getParameterTypes());
        return checkAuthorization != null;
    }

    @Override
    public <R> String getActionTreeName(final IBusinessAction<R> businessAction) {
        CheckAuthorization checkAuthorization = ReflectionUtil.getMethodAnnotationInHierarchy(CheckAuthorization.class,
                businessAction.getRunningClass(), businessAction.getDeclaredMethod().getName(),
                businessAction.getDeclaredMethod().getParameterTypes());
        ActionTreeName actionTreeName = ReflectionUtil.getAnnotationInHierarchy(businessAction.getRunningClass(),
                ActionTreeName.class);
        return actionTreeName == null ? checkAuthorization.value().value() :
                actionTreeName.value() + IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER + checkAuthorization.value().value();
    }
}
