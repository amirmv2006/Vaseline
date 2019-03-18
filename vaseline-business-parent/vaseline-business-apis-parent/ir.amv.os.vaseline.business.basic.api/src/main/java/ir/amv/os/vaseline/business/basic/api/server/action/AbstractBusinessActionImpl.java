package ir.amv.os.vaseline.business.basic.api.server.action;

import ir.amv.os.vaseline.business.basic.api.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.lang.reflect.Method;

/**
 * @author Amir
 */
public abstract class AbstractBusinessActionImpl<R> implements IBusinessAction<R> {
    private String actionId;
    private Class<?> runningClass;
    private Method declaredMethod;
    private Object[] actionParams;
    private IBusinessMetadata[] metadata;
    private String actionName;
    private Class<R> actionResultType;

    public AbstractBusinessActionImpl(
            final String actionId,
            final Class<?> runningClass,
            final Method declaredMethod,
            final Object[] actionParams,
            final IBusinessMetadata[] metadata,
            final String actionName) {
        this.actionId = actionId;
        this.runningClass = runningClass;
        this.declaredMethod = declaredMethod;
        this.actionParams = actionParams;
        this.metadata = metadata;
        this.actionName = actionName;
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), IBusinessAction.class);
        if (genericArgumentClasses.length > 0) {
            actionResultType = (Class<R>) genericArgumentClasses[0];
        }
    }

    public AbstractBusinessActionImpl(
            final String actionId,
            final Class<?> runningClass,
            final Method declaredMethod,
            final Object[] actionParams,
            final IBusinessMetadata[] metadata,
            final String actionName,
            final Class<R> actionResultType) {
        this.actionId = actionId;
        this.runningClass = runningClass;
        this.declaredMethod = declaredMethod;
        this.actionParams = actionParams;
        this.metadata = metadata;
        this.actionName = actionName;
        this.actionResultType = actionResultType;
    }

    @Override
    public String getActionId() {
        return actionId;
    }

    @Override
    public Class<?> getRunningClass() {
        return runningClass;
    }

    @Override
    public Method getDeclaredMethod() {
        return declaredMethod;
    }

    @Override
    public Object[] getActionParams() {
        return actionParams;
    }

    @Override
    public IBusinessMetadata[] getBusinessMetadata() {
        return metadata;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public Class<R> getActionResultType() {
        return actionResultType;
    }
}
