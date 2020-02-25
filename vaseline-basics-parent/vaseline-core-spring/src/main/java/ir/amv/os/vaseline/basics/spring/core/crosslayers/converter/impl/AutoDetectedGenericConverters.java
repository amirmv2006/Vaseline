package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class AutoDetectedGenericConverters implements ApplicationContextAware {
    private final Set<Class<? extends AutoDetectableGenericConverter>> converterClasses;
    private Set<AutoDetectableGenericConverter> converters;
    private ApplicationContext applicationContext;

    public AutoDetectedGenericConverters(final Set<Class<? extends AutoDetectableGenericConverter>> converterClasses) {
        this.converterClasses = converterClasses;
    }

    @PostConstruct
    public void init() {
        converters = new HashSet<>();
        for (Class<? extends AutoDetectableGenericConverter> converterClass : converterClasses) {
            AutoDetectableGenericConverter converter = createConverter(converterClass);
            converters.add(converter);
        }
    }

    private <T> T createConverter(Class<T> converterClass) {
        try {
            Constructor<T> constructor = converterClass.getDeclaredConstructor(ApplicationContext.class);
            constructor.setAccessible(true);
            return constructor.newInstance(applicationContext);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            try {
                Constructor<T> constructor = converterClass.getConstructor();
                return constructor.newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                throw new BeanCreationException("Can not create converter: " + converterClass, ex);
            }
        }
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Set<AutoDetectableGenericConverter> getConverters() {
        return converters;
    }
}
