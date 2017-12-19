package ir.amv.os.vaseline.basics.osgi.json.activator;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.apis.json.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.basics.apis.json.shared.annot.ExcludeFromJson;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.osgi.json.server.converter.VaselineJsonConverterGsonImpl;
import ir.amv.os.vaseline.basics.osgi.json.server.polymorphysm.GsonPolymorphysmSerializerAndDeserializer;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * @author Amir
 */
public class VaselineGsonServiceRegisterer {

    private Set<JsonSerializer<?>> serializers = new HashSet<>();
    private Set<JsonDeserializer<?>> deserializers = new HashSet<>();
    private Set<IVaselinePolymorphysmClassHolder> polymorphysmClassHolders = new HashSet<>();
    private BundleContext context;
    private ServiceRegistration<IVaselineJsonConverter> jsonConverterServiceRegistration;

    public VaselineGsonServiceRegisterer(final BundleContext context) {
        this.context = context;
    }

    public void addJsonSerializer(final JsonSerializer<?> jsonSerializer) {
        serializers.add(jsonSerializer);

    }

    public void removeJsonSerializer(final JsonSerializer<?> jsonSerializer) {
        serializers.remove(jsonSerializer);
    }

    public void addJsonDeserializer(final JsonDeserializer<?> jsonDeserializer) {
        deserializers.add(jsonDeserializer);
    }

    public void removeJsonDeserializer(final JsonDeserializer<?> jsonDeserializer) {
        deserializers.remove(jsonDeserializer);
    }

    public void addPolymorphysmClassHolder(final IVaselinePolymorphysmClassHolder polymorphysmClassHolder) {
        polymorphysmClassHolders.add(polymorphysmClassHolder);
    }

    public void removePolymorphysmClassHolder(final IVaselinePolymorphysmClassHolder polymorphysmClassHolder) {
        polymorphysmClassHolders.remove(polymorphysmClassHolder);
    }

    public void registerService() {
        LOGGER.log(VaselineLogLevel.INFO, "Registerin Gson Service");
        GsonBuilder gsonBuilder = new GsonBuilder();
        // no need to register as a service
        GsonPolymorphysmSerializerAndDeserializer polymorphysmSerializerAndDeserializer = new GsonPolymorphysmSerializerAndDeserializer();
        List<Class<?>> allParentClasses = new ArrayList<>();
        for (IVaselinePolymorphysmClassHolder polymorphysmClassHolder : polymorphysmClassHolders) {
            List<Class<?>> parentClasses = polymorphysmClassHolder.parentClasses();
            for (Class<?> parentClass : parentClasses) {
                if (parentClass.isAnnotationPresent(ExcludeFromJson.class)) {
                    continue;
                }
                boolean shouldSkip = false;
                for (Class<?> allParentClass : allParentClasses) {
                    if (allParentClass.isAssignableFrom(parentClass)) {
                        shouldSkip = true;
                        break;
                    }
                }
                if (shouldSkip) {
                    continue;
                }
                allParentClasses.add(parentClass);
                gsonBuilder.registerTypeAdapter(parentClass,
                        polymorphysmSerializerAndDeserializer);
            }
        }
        polymorphysmSerializerAndDeserializer.setAllParentClasses(allParentClasses);

        // in case this class is registered as a service, we remove it (FIXME not like this!)
        serializers.remove(polymorphysmSerializerAndDeserializer);
        deserializers.remove(polymorphysmSerializerAndDeserializer);

        for (JsonSerializer serializer : serializers) {
            deserializers.remove(serializer); // remove duplicates
        }
        for (JsonSerializer<?> jsonSerializer : serializers) {
            Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(jsonSerializer.getClass(), JsonSerializer.class);
            if (genericArgumentClasses != null) {
                gsonBuilder.registerTypeAdapter(genericArgumentClasses[0], jsonSerializer);
            }
        }
        for (JsonDeserializer<?> jsonDeserializer : deserializers) {
            Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(jsonDeserializer.getClass(), JsonDeserializer.class);
            if (genericArgumentClasses != null) {
                gsonBuilder.registerTypeAdapter(genericArgumentClasses[0], jsonDeserializer);
            }
        }

        gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (f.getAnnotation(ExcludeFromJson.class) != null) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        Gson gson = gsonBuilder.create();

        doRegisterVaselineJsonService(gson);
    }

    private synchronized void doRegisterVaselineJsonService(final Gson gson) {
        if (jsonConverterServiceRegistration != null) {
            jsonConverterServiceRegistration.unregister();
        }
        VaselineJsonConverterGsonImpl vaselineJsonConverterGson = new VaselineJsonConverterGsonImpl();
        vaselineJsonConverterGson.setGson(gson);
        jsonConverterServiceRegistration = context.registerService(IVaselineJsonConverter.class, vaselineJsonConverterGson, new Hashtable<>());
    }

    public synchronized void close() {
        if (jsonConverterServiceRegistration != null) {
            jsonConverterServiceRegistration.unregister();
            jsonConverterServiceRegistration = null;
        }
    }
}
