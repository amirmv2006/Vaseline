package ir.amv.os.vaseline.basics.json.gson.osgi.activator.tracker;

import com.google.gson.JsonSerializer;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.base.osgi.deferred.DeferredServiceTracker;
import ir.amv.os.vaseline.basics.json.gson.osgi.activator.GsonServiceRegistererDeferedTask;
import ir.amv.os.vaseline.basics.json.gson.osgi.activator.VaselineGsonServiceRegisterer;
import ir.amv.os.vaseline.basics.logging.common.osgi.server.helper.LOGGER;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Amir
 */
public class VaselineGsonIGsonSerializerServiceTracker extends DeferredServiceTracker<JsonSerializer<?>,
        JsonSerializer<?>> {

    public VaselineGsonIGsonSerializerServiceTracker(final BundleContext context,
                                                     final VaselineGsonServiceRegisterer gsonServiceRegisterer)
            throws InvalidSyntaxException {
        super(context, JsonSerializer.class.getName(), null,
                new GsonServiceRegistererDeferedTask<>(gsonServiceRegisterer, jsonSerializer -> {
                    LOGGER.log(VaselineLogLevel.DEBUG, "adding json serializer %s", jsonSerializer);
                    gsonServiceRegisterer.addJsonSerializer(jsonSerializer);
                }),
                new GsonServiceRegistererDeferedTask<>(gsonServiceRegisterer, jsonSerializer -> {
                    LOGGER.log(VaselineLogLevel.DEBUG, "removing json serializer %s", jsonSerializer);
                    gsonServiceRegisterer.removeJsonSerializer(jsonSerializer);
                }));
    }
}
