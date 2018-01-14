package ir.amv.os.vaseline.ws.rest.config;

import ir.amv.os.vaseline.basics.spring.json.config.VaselineJsonConfig;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import ir.amv.os.vaseline.ws.common.config.VaselineWebServiceCommonConfig;
import ir.amv.os.vaseline.ws.rest.config.application.JaxRestApplication;
import ir.amv.os.vaseline.ws.rest.config.exception.DefaultCxfExceptionMapper;
import ir.amv.os.vaseline.ws.rest.config.exclude.annot.ExcludeRestService;
import ir.amv.os.vaseline.ws.rest.config.exclude.impl.BaseExcludeRestServiceFilter;
import ir.amv.os.vaseline.ws.rest.config.gsonhandler.GsonMessageBodyHandler;
import ir.amv.os.vaseline.ws.rest.server.base.parent.IBaseRestService;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/13/2016.
 */
@Configuration
@Import({
        VaselineWebServiceCommonConfig.class,
        VaselineJsonConfig.class
})
public class VaselineWebServiceRestConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private ArrayList<Object> restBeansList;

    @Bean
    public GsonMessageBodyHandler gsonMessageBodyHandler() {
        return new GsonMessageBodyHandler();
    }

    @Bean
    public MessageBodyWriter<Object> jsonWriter(GsonMessageBodyHandler handler) {
        return handler;
    }

    @Bean
    public MessageBodyReader<Object> jsonReader(GsonMessageBodyHandler handler) {
        return handler;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    @DependsOn({ "cxf" })
    public Server jaxRsServer(
            @Qualifier("jsonWriter") MessageBodyWriter<Object> bodyWriter,
            BaseExcludeRestServiceFilter excludeRestServiceFilter, DefaultCxfExceptionMapper exceptionMapper) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance()
                .createEndpoint(jaxRsApiApplication(),
                        JAXRSServerFactoryBean.class);
        restBeansList = new ArrayList<Object>();

        Map<String, IBaseRestService> beansOfType = applicationContext.getBeansOfType(IBaseRestService.class);
        List<String> excludedBeanNames = excludeRestServiceFilter.excludedBeanNames();
        List<Class<?>> excludedBeanClasses = excludeRestServiceFilter.excludedBeanClasses();
        for (String beanName : beansOfType.keySet()) {
            if (excludedBeanNames != null && excludedBeanNames.contains(beanName)) {
                continue;
            }
            IBaseRestService restService = beansOfType.get(beanName);
            Class<? extends IBaseRestService> restServiceClass = restService.getClass();
            if (excludedBeanClasses != null) {
                boolean found = false;
                for (Class<?> excludedClass : excludedBeanClasses) {
                    if (excludedClass.isAssignableFrom(restServiceClass)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    continue;
                }
            }
            if (ReflectionUtil.getAnnotationInHierarchy(restServiceClass, ExcludeRestService.class) != null) {
                continue;
            }
            restBeansList.add(restService);
        }
        factory.setServiceBeans(restBeansList);
        factory.setAddress("/rest");
        factory.setProviders(Arrays.<Object> asList(bodyWriter, exceptionMapper));
        return factory.create();
    }

    @Bean
    DefaultCxfExceptionMapper exceptionMapper() {
        return new DefaultCxfExceptionMapper();
    }

    @Bean
    public JaxRestApplication jaxRsApiApplication() {
        return new JaxRestApplication();
    }

    @Bean
    public BaseExcludeRestServiceFilter excludeRestServiceFilter() {
        return new BaseExcludeRestServiceFilter() {
        };
    }

    public ArrayList<Object> getRestBeansList() {
        return restBeansList;
    }

}
