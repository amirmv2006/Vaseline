package ir.amv.os.vaseline.base.core.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by AMV on 2/2/2016.
 */
@Configuration
@PropertySource("classpath:vaseline.properties")
@ComponentScan("ir.amv.os.vaseline.base.core.server")
public class VaselineCoreConfig implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * returns bean of <code>beanClass</code>
     * @param beanClass
     * @param <T>
     * @return
     * @deprecated Avoid using this AFAP (i.e As Far As Possible)
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }
}
