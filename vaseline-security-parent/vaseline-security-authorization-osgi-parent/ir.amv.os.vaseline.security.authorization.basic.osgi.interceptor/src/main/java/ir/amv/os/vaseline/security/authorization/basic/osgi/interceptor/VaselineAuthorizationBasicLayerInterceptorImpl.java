package ir.amv.os.vaseline.security.authorization.basic.osgi.interceptor;

import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.common.osgi.server.helper.LOGGER;
import ir.amv.os.vaseline.basics.core.api.server.proxy.MethodExecution;
import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.IAuthorizationActionApi;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.action.IBusinessActionSecurityChecker;
import ir.amv.os.vaseline.security.authorization.basic.api.server.api.annot.ActionTreeName;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
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
    public <R> boolean isSecured(final MethodExecution methodExecution) {
        return getActionTreeName(methodExecution) != null;
    }

    @Override
    public <R> String getActionTreeName(final MethodExecution methodExecution) {
        ActionTreeName baseActionTreeNameAnnot = ReflectionUtil.getAnnotationInHierarchy(methodExecution
                        .getOriginalObject().getClass(),
                ActionTreeName.class);
        String baseActionTreeName;
        if (baseActionTreeNameAnnot == null) {
            LOGGER.log(VaselineLogLevel.WARNING,
                    "can not get base action tree name for %s, did you forget to annotate your business class with " +
                            "@ActionTreeName?",
                    methodExecution.getOriginalObject().getClass());
            baseActionTreeName = "unknown";
        } else {
            baseActionTreeName = baseActionTreeNameAnnot.value();
        }
        String actionTreeName = getActionTreeName(IBaseReadOnlyApi.class, methodExecution.getMethod());
        if (actionTreeName != null) {
            return baseActionTreeName + IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER + actionTreeName;
        }
        actionTreeName = getActionTreeName(IBaseCrudApi.class, methodExecution.getMethod());
        if (actionTreeName != null) {
            return baseActionTreeName + IAuthorizationActionApi.ACTION_TREE_NAME_SPLITTER + actionTreeName;
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
