package ir.amv.os.vaseline.basics.spring.core.config;

import ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.IProxyAware;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class ProxyAwareBeanPostProcessor implements BeanPostProcessor, Ordered {

    private int order = Ordered.LOWEST_PRECEDENCE;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //// FIXME: AMV 4/17/2016 remove this
        if (beanName.equalsIgnoreCase("clientDetailsService")) {
            return bean;
        }

        Object targetBean = getTargetBean(bean);

        if (targetBean instanceof IProxyAware) {
            IProxyAware baseApi = (IProxyAware) targetBean;
            baseApi.setProxy(bean);
        }

        return bean;
    }


    private Object getTargetBean(Object bean) {
        Object target = bean;

        if (target instanceof Advised) {
            Advised advised = (Advised) target;
            try {
                target = advised.getTargetSource().getTarget();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        return target;
    }


    @Override
    public int getOrder() {
        return order;
    }

}