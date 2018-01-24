package ir.amv.os.vaseline.business.apis.basic.layer.server.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;

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
    R execute() throws BaseVaselineServerException;

    // calculated
    String getActionName(); // method name
    Class<R> getActionResultType(); // method return type
}
