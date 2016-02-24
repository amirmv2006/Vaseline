package ir.amv.os.vaseline.base.json.config;

import com.google.gson.*;
import ir.amv.os.vaseline.base.core.config.VaselineCoreConfig;
import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionUtil;
import ir.amv.os.vaseline.base.json.server.annot.ExcludeFromJson;
import ir.amv.os.vaseline.base.json.server.polymorphysm.GsonPolymorphysmSerializerAndDeserializer;
import ir.amv.os.vaseline.base.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.base.core.server.polymorphysm.impl.VaselinePolymorphysmClassHolderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/3/2016.
 */
@Configuration
@Import(VaselineCoreConfig.class)
@ComponentScan("ir.amv.os.vaseline.base.json.server")
public class VaselineJsonConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Autowired
    protected GsonBuilder gsonBuilder(
            List<IVaselinePolymorphysmClassHolder> polymorphysmClassHolders,
            Map<String, JsonSerializer<?>> gsonSerializers,
            Map<String, JsonDeserializer<?>> gsonDeserializers) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        GsonPolymorphysmSerializerAndDeserializer polymorphysmSerializerAndDeserializer = childSerializerAndDeserializer();
        List<Class<?>> allParentClasses = new ArrayList<Class<?>>();
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

        gsonSerializers.remove("vaselinePolymorphysmSerializerAndDeserializer");
        gsonDeserializers.remove("vaselinePolymorphysmSerializerAndDeserializer");
        for (String beanName : gsonSerializers.keySet()) {
            gsonDeserializers.remove(beanName);
        }
        for (JsonSerializer<?> jsonSerializer : gsonSerializers.values()) {
            Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(jsonSerializer.getClass(), JsonSerializer.class);
            if (genericArgumentClasses != null) {
                gsonBuilder.registerTypeAdapter(genericArgumentClasses[0], jsonSerializer);
            }
        }
        for (JsonDeserializer<?> jsonDeserializer : gsonDeserializers.values()) {
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
        // didn't work, stackoverflow!
        // GsonLazySerializerAndDeserializer gsonLazySerializer =
        // gsonLazySerializer();
        // gsonBuilder.registerTypeHierarchyAdapter(HibernateProxy.class,
        // gsonLazySerializer);
        // gsonBuilder.registerTypeHierarchyAdapter(PersistentSet.class,
        // gsonLazySerializer);
        // gsonBuilder.registerTypeHierarchyAdapter(PersistentList.class,
        // gsonLazySerializer);
        return gsonBuilder;
    }

    @Bean(name = "vaselinePolymorphysmSerializerAndDeserializer")
    public GsonPolymorphysmSerializerAndDeserializer childSerializerAndDeserializer() {
        return new GsonPolymorphysmSerializerAndDeserializer();
    }

    @Bean
    public IVaselinePolymorphysmClassHolder defaultVaselinePolymorphysmClassHolder() {
        return new VaselinePolymorphysmClassHolderImpl(new ArrayList<Class<?>>());
    }
}
