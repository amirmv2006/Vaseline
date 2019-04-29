package ir.amv.os.vaseline.security.authorization.basic.osgi.interceptor;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyInterceptor;
import ir.amv.os.vaseline.basics.core.api.server.proxy.MethodExecution;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.action.IBusinessActionSecurityChecker;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.annot.NoAuthorization;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IProxyInterceptor.class
)
public class VaselineAuthorizationInterceptorImpl
        implements IProxyInterceptor<Void> {

    private IAuthorizationApi authorizationApi;
    private List<IBusinessActionSecurityChecker> securityCheckers = new ArrayList<>();

    @Override
    public boolean appliesTo(final MethodExecution methodExecution) {
        NoAuthorization noAuthorization = ReflectionUtil.getMethodAnnotationInHierarchy(NoAuthorization.class,
                methodExecution.getOriginalObject().getClass(), methodExecution.getMethod().getName(),
                methodExecution.getMethod().getParameterTypes());
        if (noAuthorization != null) {
            return false;
        }
        for (IBusinessActionSecurityChecker actionSecured : securityCheckers) {
            if (actionSecured.isSecured(methodExecution)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Void preExecute(final MethodExecution methodExecution) throws BaseVaselineServerException {
        for (IBusinessActionSecurityChecker actionSecured : securityCheckers) {
            if (actionSecured.isSecured(methodExecution)) {
                authorizationApi.checkAuthorization(actionSecured.getActionTreeName(methodExecution));
            }
        }
        return null;
    }

    @Override
    public <R> void postExecuteSuccessfully(final MethodExecution methodExecution, final R returnedValue, final Void preExecResult) throws BaseVaselineServerException {
    }

    @Override
    public void postExecuteException(final MethodExecution methodExecution, final Throwable t, final Void preExecResult) throws BaseVaselineServerException {
    }

    @Reference(
            cardinality = ReferenceCardinality.AT_LEAST_ONE
    )
    public void addBusinessActionSecurityChecker(final IBusinessActionSecurityChecker securityChecker) {
        securityCheckers.add(securityChecker);
    }

    public void removeBusinessActionSecurityChecker(final IBusinessActionSecurityChecker securityChecker) {
        securityCheckers.remove(securityChecker);
    }

    @Reference
    public void setAuthorizationApi(final IAuthorizationApi authorizationApi) {
        this.authorizationApi = authorizationApi;
    }
}
