package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.executor;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessExecutorInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amir
 */
public interface IImplementedBusinessActionExecutor
        extends IVaselineBusinessActionExecutor {

    List<IVaselineBusinessExecutorInterceptor> getBusinessExecutorInterceptors();

    @Override
    default  <R> R executeAction(final IBusinessAction<R> action) throws BaseVaselineServerException {
        List<IVaselineBusinessExecutorInterceptor> interceptorList = getBusinessExecutorInterceptors();
        Map<IVaselineBusinessExecutorInterceptor, Object> preCallMap = new HashMap<>(interceptorList.size());
        for (IVaselineBusinessExecutorInterceptor interceptor : interceptorList) {
            if (interceptor.appliesTo(action)) {
                Object preExecuteResult = interceptor.preExecute(action);
                preCallMap.put(interceptor, preExecuteResult);
            }
        }
        R result;
        try {
            result = action.execute(this);
            for (IVaselineBusinessExecutorInterceptor interceptor : preCallMap.keySet()) {
                if (interceptor.appliesTo(action)) {
                    interceptor.postExecuteSuccessfully(action, result, preCallMap.get(interceptor));
                }
            }
        } catch (Throwable t) {
            for (IVaselineBusinessExecutorInterceptor interceptor : preCallMap.keySet()) {
                if (interceptor.appliesTo(action)) {
                    interceptor.postExecuteException(action, t, preCallMap.get(interceptor));
                }
            }
            if (t instanceof BaseVaselineServerException) {
                BaseVaselineServerException serverException = (BaseVaselineServerException) t;
                throw serverException;
            } else {
                throw new BaseVaselineServerException(t);
            }
        }
        return result;
    }

    default void addBusinessInterceptor(IVaselineBusinessExecutorInterceptor interceptor) {
        if (!getBusinessExecutorInterceptors().contains(interceptor)) {
            getBusinessExecutorInterceptors().add(interceptor);
        }
    }

    default void removeBusinessInterceptor(IVaselineBusinessExecutorInterceptor interceptor) {
        getBusinessExecutorInterceptors().remove(interceptor);
    }
}
