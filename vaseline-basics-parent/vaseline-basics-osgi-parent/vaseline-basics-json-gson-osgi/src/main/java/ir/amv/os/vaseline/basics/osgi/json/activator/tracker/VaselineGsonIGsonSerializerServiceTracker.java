package ir.amv.os.vaseline.basics.osgi.json.activator.tracker;

import com.google.gson.JsonSerializer;
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
public class VaselineGsonIGsonSerializerServiceTracker extends DeferredServiceTracker<JsonSerializer<?>,
        JsonSerializer<?>> {

    public VaselineGsonIGsonSerializerServiceTracker(final BundleContext context,
                                                     final VaselineGsonServiceRegisterer gsonServiceRegisterer)
            throws InvalidSyntaxException {
        super(context, context.createFilter("(objectClass=" + JsonSerializer.class.getName() + ")"), null,
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
