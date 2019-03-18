package ir.amv.os.vaseline.security.authorization.basic.osgi.interceptor;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessExecutorInterceptor;
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
        service = IVaselineBusinessExecutorInterceptor.class
)
public class VaselineAuthorizationInterceptorImpl
        implements IVaselineBusinessExecutorInterceptor<Void> {

    private IAuthorizationApi authorizationApi;
    private List<IBusinessActionSecurityChecker> securityCheckers = new ArrayList<>();

    @Override
    public <R> boolean appliesTo(final IBusinessAction<R> businessAction) {
        NoAuthorization noAuthorization = ReflectionUtil.getMethodAnnotationInHierarchy(NoAuthorization.class,
                businessAction.getRunningClass(), businessAction.getDeclaredMethod().getName(),
                businessAction.getDeclaredMethod().getParameterTypes());
        if (noAuthorization != null) {
            return false;
        }
        for (IBusinessActionSecurityChecker actionSecured : securityCheckers) {
            if (actionSecured.isSecured(businessAction)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <R> Void preExecute(final IBusinessAction<R> businessAction) throws BaseVaselineServerException {
        for (IBusinessActionSecurityChecker actionSecured : securityCheckers) {
            if (actionSecured.isSecured(businessAction)) {
                authorizationApi.checkAuthorization(actionSecured.getActionTreeName(businessAction));
            }
        }
        return null;
    }

    @Override
    public <R> void postExecuteSuccessfully(final IBusinessAction<R> businessAction, final R returnedValue, final Void preExecResult) throws BaseVaselineServerException {
    }

    @Override
    public <R> void postExecuteException(final IBusinessAction<R> businessAction, final Throwable t, final Void preExecResult) throws BaseVaselineServerException {
    }

    @Override
    public Class<Void> tokenClass() {
        return Void.class;
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
