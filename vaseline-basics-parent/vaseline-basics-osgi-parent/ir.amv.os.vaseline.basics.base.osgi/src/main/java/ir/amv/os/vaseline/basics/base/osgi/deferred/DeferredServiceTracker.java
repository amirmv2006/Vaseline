package ir.amv.os.vaseline.basics.base.osgi.deferred;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Amir
 */
public class DeferredServiceTracker<S, T> extends ServiceTracker<S, T> {

    public DeferredServiceTracker(final BundleContext context, final ServiceReference<S> reference, final ServiceTrackerCustomizer<S, T> customizer, final IDeferredTask<S> addServiceConsumer, final IDeferredTask<S> removeServiceConsumer) {
        super(context, reference, customizer);
        this.addServiceConsumer = addServiceConsumer;
        this.removeServiceConsumer = removeServiceConsumer;
    }

    public DeferredServiceTracker(final BundleContext context, final String clazz, final ServiceTrackerCustomizer<S, T> customizer, final IDeferredTask<S> addServiceConsumer, final IDeferredTask<S> removeServiceConsumer) {
        super(context, clazz, customizer);
        this.addServiceConsumer = addServiceConsumer;
        this.removeServiceConsumer = removeServiceConsumer;
    }

    public DeferredServiceTracker(final BundleContext context, final Filter filter, final ServiceTrackerCustomizer<S, T> customizer, final IDeferredTask<S> addServiceConsumer, final IDeferredTask<S> removeServiceConsumer) {
        super(context, filter, customizer);
        this.addServiceConsumer = addServiceConsumer;
        this.removeServiceConsumer = removeServiceConsumer;
    }

    public DeferredServiceTracker(final BundleContext context, final Class<S> clazz, final ServiceTrackerCustomizer<S, T> customizer, final IDeferredTask<S> addServiceConsumer, final IDeferredTask<S> removeServiceConsumer) {
        super(context, clazz, customizer);
        this.addServiceConsumer = addServiceConsumer;
        this.removeServiceConsumer = removeServiceConsumer;
    }

    protected final Map<String, IDeferredTask> activatorTaskMap = new Hashtable<>();
    protected final Map<String, Object> activatorTaskLockObjectMap = new Hashtable<>();
    private IDeferredTask addServiceConsumer;
    private IDeferredTask removeServiceConsumer;
    private final Timer deferedTasksTimer = new Timer(false);

    protected void addDeferredTask(
            String taskName,
            IDeferredTask task
    ) {
        activatorTaskMap.putIfAbsent(taskName, task);
        activatorTaskLockObjectMap.putIfAbsent(taskName, new Object());
    }

    protected void deferActivatorTask(
            String taskName,
            final S service,
            IDeferredTask task,
            long delay
    ) {
        // no need to synchronize this, cause we want to add to the execution queue even if it's being executed now
        activatorTaskMap.putIfAbsent(taskName, task);
        synchronized (activatorTaskLockObjectMap.get(taskName)) {
            task.executeNow(service);
        }
        deferedTasksTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (activatorTaskLockObjectMap.get(taskName)) {
                    IDeferredTask task = activatorTaskMap.get(taskName);
                    if (task != null) { // if someone else didn't finish already
                        task.executeLater(service);
                        activatorTaskMap.remove(taskName);
                    } else {
                        System.out.println("Done Already");
                    }
                }
            }
        }, delay);
    }

    @Override
    public void close() {
        activatorTaskMap.clear();
        activatorTaskLockObjectMap.clear();
        deferedTasksTimer.cancel();
        super.close();
    }

    @Override
    public T addingService(final ServiceReference<S> reference) {
        String taskName = "registerService";
        addDeferredTask(taskName, addServiceConsumer);
        S service = context.getService(reference);
        synchronized (activatorTaskLockObjectMap.get(taskName)) {
            deferActivatorTask(taskName, service, addServiceConsumer, 2000);
        }
        return super.addingService(reference);
    }

    @Override
    public void removedService(final ServiceReference<S> reference, final T service) {
        String taskName = "unregisterService";
        addDeferredTask(taskName, removeServiceConsumer);
        S contextService = context.getService(reference);
        synchronized (activatorTaskLockObjectMap.get(taskName)) {
            deferActivatorTask(taskName, contextService, removeServiceConsumer, 2000);
        }
        super.removedService(reference, service);
    }

}
