package ir.amv.os.vaseline.business.basic.api.server.action;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.IBusinessMetadata;

import java.lang.reflect.Method;

/**
 * @author Amir
 */
public interface IBusinessAction<R> {

    String getActionId();
    Class<?> getRunningClass();
    Method getDeclaredMethod();
    Object[] getActionParams();
    IBusinessMetadata[] getBusinessMetadata();
    R execute(final IVaselineBusinessActionExecutor businessActionExecutor) throws BaseVaselineServerException;

    // calculated
    String getActionName(); // method name
    Class<R> getActionResultType(); // method return type
}
