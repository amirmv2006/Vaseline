package ir.amv.os.vaseline.basics.json.gson.osgi.activator.tracker;

import ir.amv.os.vaseline.basics.core.api.server.polymorphysm.IVaselinePolymorphysmClassHolder;
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
public class VaselineGsonIPolymorphysmClassHolderServiceTracker extends DeferredServiceTracker<IVaselinePolymorphysmClassHolder,
        IVaselinePolymorphysmClassHolder> {

    public VaselineGsonIPolymorphysmClassHolderServiceTracker(final BundleContext context,
                                                              final VaselineGsonServiceRegisterer gsonServiceRegisterer)
            throws InvalidSyntaxException {
        super(context, IVaselinePolymorphysmClassHolder.class, null,
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
