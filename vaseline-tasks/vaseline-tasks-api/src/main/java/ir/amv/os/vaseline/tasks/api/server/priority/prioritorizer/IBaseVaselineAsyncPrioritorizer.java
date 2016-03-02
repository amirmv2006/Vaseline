package ir.amv.os.vaseline.tasks.api.server.priority.prioritorizer;

import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by AMV on 2/29/2016.
 */
public interface IBaseVaselineAsyncPrioritorizer {

    int getPriority(MethodInvocation methodInvocation);
}
