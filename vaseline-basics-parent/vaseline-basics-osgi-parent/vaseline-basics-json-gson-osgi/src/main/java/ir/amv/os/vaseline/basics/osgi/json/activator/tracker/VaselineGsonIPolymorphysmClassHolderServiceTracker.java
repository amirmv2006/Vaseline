package ir.amv.os.vaseline.basics.osgi.json.activator.tracker;

import com.google.gson.JsonSerializer;
import ir.amv.enterprise.osgi.bundle.base.api.activator.defered.DeferedServiceTracker;
import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.osgi.json.activator.GsonServiceRegistererDeferedTask;
import ir.amv.os.vaseline.basics.osgi.json.activator.VaselineGsonServiceRegisterer;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Amir
 */
public class VaselineGsonIPolymorphysmClassHolderServiceTracker extends DeferedServiceTracker<IVaselinePolymorphysmClassHolder,
        IVaselinePolymorphysmClassHolder> {

    public VaselineGsonIPolymorphysmClassHolderServiceTracker(final BundleContext context,
                                                              final VaselineGsonServiceRegisterer gsonServiceRegisterer)
            throws InvalidSyntaxException {
        super(context, context.createFilter("(objectClass=" + IVaselinePolymorphysmClassHolder.class.getName() + ")"), null,
                new GsonServiceRegistererDeferedTask<>(gsonServiceRegisterer,
                        polymorphysmClassHolder -> {
                            LOGGER.log(VaselineLogLevel.INFO, "adding polymorphism class holder %s", polymorphysmClassHolder);
                            gsonServiceRegisterer.addPolymorphysmClassHolder(polymorphysmClassHolder);
                        }),
                new GsonServiceRegistererDeferedTask<>(gsonServiceRegisterer,
                        polymorphysmClassHolder -> {
                            LOGGER.log(VaselineLogLevel.INFO, "removing polymorphism class holder %s",
                                    polymorphysmClassHolder);
                            gsonServiceRegisterer.removePolymorphysmClassHolder(polymorphysmClassHolder);
                        }));
    }
}
