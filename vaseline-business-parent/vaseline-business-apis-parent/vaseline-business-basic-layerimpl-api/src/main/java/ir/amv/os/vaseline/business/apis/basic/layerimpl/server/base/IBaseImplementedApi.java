package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layer.server.base.IBaseApi;

import java.lang.reflect.Method;

public interface IBaseImplementedApi
        extends IBaseApi {

    IVaselineBusinessActionExecutor getBusinessActionExecutor();

    @Override
    default <T> T doBusinessAction(IBusinessAction<T> businessAction) throws BaseVaselineServerException {
        return getBusinessActionExecutor().executeAction(businessAction);
    }

    default Method getDeclaredMethod(Class<?> aClass, String methodName, Class<?>... paramTypes) throws
            BaseVaselineServerException {
        try {
            return aClass.getDeclaredMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            throw new BaseVaselineServerException(e);
        }
    }
}
