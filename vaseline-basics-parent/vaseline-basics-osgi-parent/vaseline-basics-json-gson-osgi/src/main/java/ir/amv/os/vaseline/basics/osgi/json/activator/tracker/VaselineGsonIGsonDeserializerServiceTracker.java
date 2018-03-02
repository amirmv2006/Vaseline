package ir.amv.os.vaseline.basics.osgi.json.activator.tracker;

import com.google.gson.JsonDeserializer;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.osgi.base.deferred.DeferredServiceTracker;
import ir.amv.os.vaseline.basics.osgi.json.activator.GsonServiceRegistererDeferedTask;
import ir.amv.os.vaseline.basics.osgi.json.activator.VaselineGsonServiceRegisterer;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Amir
 */
public class VaselineGsonIGsonDeserializerServiceTracker extends DeferredServiceTracker<JsonDeserializer<?>,
        JsonDeserializer<?>> {

    public VaselineGsonIGsonDeserializerServiceTracker(final BundleContext context,
                                                       final VaselineGsonServiceRegisterer gsonServiceRegisterer)
            throws InvalidSyntaxException {
        super(context, JsonDeserializer.class.getName(), null,
                new GsonServiceRegistererDeferedTask<>(gsonServiceRegisterer, jsonDeserializer -> {
                    LOGGER.log(VaselineLogLevel.DEBUG, "adding json deserializer %s", jsonDeserializer);
                    gsonServiceRegisterer.addJsonDeserializer(jsonDeserializer);
                }),
                new GsonServiceRegistererDeferedTask<>(gsonServiceRegisterer, jsonDeserializer -> {
                    LOGGER.log(VaselineLogLevel.DEBUG, "removing json deserializer %s", jsonDeserializer);
                    gsonServiceRegisterer.removeJsonDeserializer(jsonDeserializer);
                }));
    }
}
