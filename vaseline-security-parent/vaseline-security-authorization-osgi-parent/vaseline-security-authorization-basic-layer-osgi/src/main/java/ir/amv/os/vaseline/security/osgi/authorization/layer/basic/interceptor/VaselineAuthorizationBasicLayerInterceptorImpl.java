package ir.amv.os.vaseline.security.osgi.authorization.layer.basic.interceptor;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.api.action.IBusinessActionSecurityChecker;
import org.osgi.service.component.annotations.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IBusinessActionSecurityChecker.class
)
public class VaselineAuthorizationBasicLayerInterceptorImpl
        implements IBusinessActionSecurityChecker {

    public static final String READ_ACTION_NAME = "read";
    public static final String CREATE_ACTION_NAME = "create";
    public static final String UPDATE_ACTION_NAME = "update";
    public static final String DELETE_ACTION_NAME = "delete";

    @Override
    public <R> boolean isSecured(final IBusinessAction<R> businessAction) {
        return getActionTreeName(businessAction) != null;
    }

    @Override
    public <R> String getActionTreeName(final IBusinessAction<R> businessAction) {
        String actionTreeName = getActionTreeName(IBaseReadOnlyApi.class, businessAction.getDeclaredMethod());
        if (actionTreeName != null) {
            return actionTreeName;
        }
        actionTreeName = getActionTreeName(IBaseCrudApi.class, businessAction.getDeclaredMethod());
        if (actionTreeName != null) {
            return actionTreeName;
        }
        return null;
    }

    private String getActionTreeName(Class<?> securedApiClass, Method declaredMethod) {
        Method[] apiMethods = securedApiClass.getDeclaredMethods();
        for (Method apiMethod : apiMethods) {
            if (apiMethod.getName().equals(declaredMethod.getName()) &&
                    Arrays.equals(apiMethod.getParameterTypes(), declaredMethod.getParameterTypes())) {
                return getActionTreeNameBasedOnMethod(declaredMethod);
            }
        }
        return null; // TODO maybe throw exception?
    }

    private String getActionTreeNameBasedOnMethod(final Method declaredMethod) {
        if (declaredMethod.getName().startsWith("get") ||
                declaredMethod.getName().startsWith("count") ||
                declaredMethod.getName().startsWith("scroll")
                ) {
            return READ_ACTION_NAME;
        }
        if (declaredMethod.getName().startsWith("save")) {
            return CREATE_ACTION_NAME;
        }
        if (declaredMethod.getName().startsWith("update")) {
            return UPDATE_ACTION_NAME;
        }
        if (declaredMethod.getName().startsWith("delete")) {
            return DELETE_ACTION_NAME;
        }
        return null; // TODO maybe throw exception?
    }
}
