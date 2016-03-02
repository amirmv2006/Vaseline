package ir.amv.os.vaseline.tasks.api.server.priority.callable;

import ir.amv.os.vaseline.tasks.api.config.executor.PriorityExecutor;

import java.util.concurrent.Callable;

/**
 * Created by AMV on 2/29/2016.
 */
public abstract class PriorityCallable
        implements Callable<Object>, PriorityExecutor.Important{

    private int priority;

    public PriorityCallable(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
