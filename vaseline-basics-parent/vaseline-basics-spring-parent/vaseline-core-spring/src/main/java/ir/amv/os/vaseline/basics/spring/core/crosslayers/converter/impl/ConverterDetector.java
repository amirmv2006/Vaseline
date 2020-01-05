package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConverterDetector
        extends ClassPathScanningCandidateComponentProvider
        implements ImportBeanDefinitionRegistrar, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ConverterDetector() {
        super(false);
        addIncludeFilter(new AssignableTypeFilter(AutoDetectableGenericConverter.class));
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes enableAnnAttr =
                AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableAutoConverterDetector.class.getName(), false));
        Set<BeanDefinition> candidateComponents = findCandidateComponents(enableAnnAttr.getString("basePackage"));
        List<AutoDetectableGenericConverter> converters = new ArrayList<>();
        for (BeanDefinition candidateComponent : candidateComponents) {
            try {
                Class<? extends AutoDetectableGenericConverter> beanClass =
                        (Class<? extends AutoDetectableGenericConverter>) Class.forName(candidateComponent.getBeanClassName());
                AutoDetectableGenericConverter converter = createConverter(beanClass);
                converters.add(converter);
            } catch (ClassNotFoundException e) {
                throw new BeanCreationException("Problem create Conversion Service", e);
            }
        }
        BeanDefinitionBuilder conversionSvc = BeanDefinitionBuilder.genericBeanDefinition(ConversionServiceFactoryBean.class)
                .addPropertyValue("converters", new HashSet<>(converters));
        registry.registerBeanDefinition("vaselineConversionSvc", conversionSvc.getBeanDefinition());
    }

    private <T> T createConverter(Class<T> converterClass) {
        try {
            Constructor<T> constructor = converterClass.getConstructor(ApplicationContext.class);
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
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
