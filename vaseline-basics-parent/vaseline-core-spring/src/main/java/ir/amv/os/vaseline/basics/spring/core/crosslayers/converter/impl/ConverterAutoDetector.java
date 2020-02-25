package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConverterAutoDetector
        extends ClassPathScanningCandidateComponentProvider
        implements ImportBeanDefinitionRegistrar {

    public ConverterAutoDetector() {
        super(false);
        addIncludeFilter(new AssignableTypeFilter(AutoDetectableGenericConverter.class));
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes enableAnnAttr =
                AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableAutoConverterDetector.class.getName(), false));
        Set<BeanDefinition> candidateComponents = findCandidateComponents(enableAnnAttr.getString("basePackage"));
        List<Class<? extends AutoDetectableGenericConverter>> converters = new ArrayList<>();
        for (BeanDefinition candidateComponent : candidateComponents) {
            try {
                Class<? extends AutoDetectableGenericConverter> beanClass =
                        (Class<? extends AutoDetectableGenericConverter>) Class.forName(candidateComponent.getBeanClassName());
                converters.add(beanClass);
            } catch (ClassNotFoundException e) {
                throw new BeanCreationException("Problem create Conversion Service", e);
            }
        }
        BeanDefinitionBuilder conversionSvc = BeanDefinitionBuilder.genericBeanDefinition(AutoDetectedGenericConverters.class)
                .addConstructorArgValue(new HashSet<>(converters));
        registry.registerBeanDefinition("autoDetectedGenericConverters", conversionSvc.getBeanDefinition());
    }

}
