package ir.amv.os.vaseline.basics.osgi.json.activator;

import ir.amv.enterprise.osgi.bundle.base.api.activator.defered.IDeferedTask;
import ir.amv.os.vaseline.basics.apis.logging.server.constants.LoggerConstants;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;

import java.util.function.Consumer;

/**
 * @author Amir
 */

public class GsonServiceRegistererDeferedTask<S> implements IDeferedTask<S> {

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
