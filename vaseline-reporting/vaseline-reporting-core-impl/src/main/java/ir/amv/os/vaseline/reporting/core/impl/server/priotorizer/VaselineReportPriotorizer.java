package ir.amv.os.vaseline.reporting.core.impl.server.priotorizer;

import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.reporting.api.server.datasource.PaginatorDataSource;
import ir.amv.os.vaseline.tasks.api.server.priority.prioritorizer.IBaseVaselineAsyncPrioritorizer;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by AMV on 2/29/2016.
 */
@Component
public class VaselineReportPriotorizer
        implements IBaseVaselineAsyncPrioritorizer{
    @Override
    public int getPriority(MethodInvocation methodInvocation) {
        Object[] arguments = methodInvocation.getArguments();
        for (Object argument : arguments) {
            if (argument instanceof PaginatorDataSource) {
                final AtomicInteger countResult = new AtomicInteger(0);
                PaginatorDataSource dataSource = (PaginatorDataSource) argument;
                IBaseCallback<IBaseCallback<Integer, Void>, Void> countCallback = dataSource.getCountCallback();
                countCallback.onSuccess(new BaseCallbackImpl<Integer, Void>() {
                    @Override
                    public void onSuccess(Integer result) {
                        countResult.set(result);
                    }
                });
                int count = countResult.get();
                if (count / (100 * 1000) == 0) {
                    return 100;
                }
                if (count / (1 * 1000 * 1000) == 0) {
                    return 10;
                }
                return  0;
            }
        }
        return 0;
    }
}
