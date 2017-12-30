package ir.amv.os.vaseline.basics.osgi.json.activator;

import ir.amv.os.vaseline.basics.osgi.base.deferred.IDeferredTask;

import java.util.function.Consumer;

/**
 * @author Amir
 */

public class GsonServiceRegistererDeferedTask<S> implements IDeferredTask<S> {

    private VaselineGsonServiceRegisterer gsonServiceRegisterer;
    private Consumer<S> consumer;

    public GsonServiceRegistererDeferedTask(final VaselineGsonServiceRegisterer gsonServiceRegisterer, Consumer<S>
            consumer) {
        this.gsonServiceRegisterer = gsonServiceRegisterer;
        this.consumer = consumer;
    }

    @Override
    public void executeNow(final S s) {
        consumer.accept(s);
    }

    @Override
    public void executeLater(S s) {
        gsonServiceRegisterer.registerService();
    }
}
