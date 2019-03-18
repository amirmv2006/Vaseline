package ir.amv.os.vaseline.business.basic.def.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.basic.api.server.base.IBaseApi;

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
