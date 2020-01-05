package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl;

import org.springframework.context.ApplicationContext;

public abstract class NeedsBeansGenericConverter
        implements AutoDetectableGenericConverter {

    private final ApplicationContext applicationContext;

    protected NeedsBeansGenericConverter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected <B> B getBean(Class<B> beanClass) {
        return applicationContext.getBean(beanClass);
    }
}
