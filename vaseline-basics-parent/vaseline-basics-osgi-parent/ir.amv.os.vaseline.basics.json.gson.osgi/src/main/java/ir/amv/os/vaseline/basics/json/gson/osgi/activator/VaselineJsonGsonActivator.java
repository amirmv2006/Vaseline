package ir.amv.os.vaseline.basics.json.gson.osgi.activator;

import ir.amv.os.vaseline.basics.core.api.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.core.api.server.polymorphysm.defimpl.VaselinePolymorphysmClassHolderImpl;
import ir.amv.os.vaseline.basics.base.osgi.AbstractBundleActivator;
import ir.amv.os.vaseline.basics.json.gson.osgi.activator.tracker.VaselineGsonIGsonDeserializerServiceTracker;
import ir.amv.os.vaseline.basics.json.gson.osgi.activator.tracker.VaselineGsonIGsonSerializerServiceTracker;
import ir.amv.os.vaseline.basics.json.gson.osgi.activator.tracker.VaselineGsonIPolymorphysmClassHolderServiceTracker;
import org.osgi.framework.BundleContext;

/**
 * @author Amir
 */
public class VaselineJsonGsonActivator extends AbstractBundleActivator {

    private VaselineGsonServiceRegisterer gsonServiceRegisterer;
    private VaselineGsonIGsonSerializerServiceTracker gsonSerializerServiceTracker;
    private VaselineGsonIGsonDeserializerServiceTracker gsonDeserializerServiceTracker;
    private VaselineGsonIPolymorphysmClassHolderServiceTracker polymorphysmClassHolderServiceTracker;

    @Override
    public void doStart(final BundleContext context) throws Exception {
        gsonServiceRegisterer = new VaselineGsonServiceRegisterer(context);
        gsonSerializerServiceTracker = new VaselineGsonIGsonSerializerServiceTracker(context, gsonServiceRegisterer);
        gsonSerializerServiceTracker.open();
        gsonDeserializerServiceTracker = new VaselineGsonIGsonDeserializerServiceTracker(context, gsonServiceRegisterer);
        gsonDeserializerServiceTracker.open();
        polymorphysmClassHolderServiceTracker = new VaselineGsonIPolymorphysmClassHolderServiceTracker(context, gsonServiceRegisterer);
        polymorphysmClassHolderServiceTracker.open();
        createDefaultVaselinePolymorphysmClassHolder();
    }

    private void createDefaultVaselinePolymorphysmClassHolder() {
        VaselinePolymorphysmClassHolderImpl polymorphysmClassHolder = new VaselinePolymorphysmClassHolderImpl();
        registerService(IVaselinePolymorphysmClassHolder.class, polymorphysmClassHolder);
    }

    @Override
    public void doStop(final BundleContext context) throws Exception {
        gsonSerializerServiceTracker.close();
        gsonDeserializerServiceTracker.close();
        polymorphysmClassHolderServiceTracker.close();
        gsonServiceRegisterer.close();
    }
}
