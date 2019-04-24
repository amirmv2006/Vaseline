package ir.amv.os.vaseline.security.authorization.basic.osgi.action;

import ir.amv.os.vaseline.basics.core.api.server.proxy.MethodExecution;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.action.IBusinessActionSecurityChecker;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.annot.ActionTreeName;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.annot.CheckAuthorization;
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
    public <R> boolean isSecured(final MethodExecution methodExecution) {
        CheckAuthorization checkAuthorization = ReflectionUtil.getMethodAnnotationInHierarchy(CheckAuthorization.class,
                methodExecution.getOriginalObject().getClass(), methodExecution.getMethod().getName(),
                methodExecution.getMethod().getParameterTypes());
        return checkAuthorization != null;
    }

    @Override
    public <R> String getActionTreeName(final MethodExecution methodExecution) {
        CheckAuthorization checkAuthorization = ReflectionUtil.getMethodAnnotationInHierarchy(CheckAuthorization.class,
                methodExecution.getOriginalObject().getClass(), methodExecution.getMethod().getName(),
                methodExecution.getMethod().getParameterTypes());
        ActionTreeName actionTreeName = ReflectionUtil.getAnnotationInHierarchy(methodExecution.getOriginalObject().getClass(),
                ActionTreeName.class);
        return actionTreeName == null ? checkAuthorization.value().value() :
                actionTreeName.value() + IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER + checkAuthorization.value().value();
    }
}
