package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action;

import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.lang.reflect.Method;

/**
 * @author Amir
 */
public abstract class AbstractBusinessActionImpl<R>
        implements IBusinessAction<R> {

    private final String actionId;
    private final Class<?> runningClass;
    private final Method declaredMethod;
    private final String actionName;
    private final Object[] actionParams;
    private final Class<R> actionResultType;
    private final IBusinessMetadata[] businessMetadata;


    public AbstractBusinessActionImpl(
            final String actionId,
            final Class<?> runningClass,
            final Method declaredMethod,
            final Object[] actionParams,
            final IBusinessMetadata... businessMetadata) {
        this.actionId = actionId;
        this.runningClass = runningClass;
        this.declaredMethod = declaredMethod;
        this.actionParams = actionParams;
        this.businessMetadata = businessMetadata;
        this.actionName = declaredMethod.getName();
        actionResultType = (Class<R>) declaredMethod.getReturnType();
    }

    @Override
    public String getActionId() {
        return actionId;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public Object[] getActionParams() {
        return actionParams;
    }

    @Override
    public Class<R> getActionResultType() {
        return actionResultType;
    }

    @Override
    public IBusinessMetadata[] getBusinessMetadata() {
        return businessMetadata;
    }

    @Override
    public Class<?> getRunningClass() {
        return runningClass;
    }

    @Override
    public Method getDeclaredMethod() {
        return declaredMethod;
    }
}
