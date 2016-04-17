package ir.amv.os.vaseline.base.core.server.proxyaware.beanpostprocessor;

import ir.amv.os.vaseline.base.core.server.proxyaware.IProxyAware;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class ProxyAwareBeanPostProcessor implements BeanPostProcessor, Ordered {

    private int order = Ordered.LOWEST_PRECEDENCE;


    public ProxyAwareBeanPostProcessor() {}


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
            target = ((Advised) target).getTargetSource();

            if (target instanceof TargetSource) {
                try {
                    target = ((TargetSource) target).getTarget();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        return target;
    }


    @Override
    public int getOrder() {
        return order;
    }


    public void setOrder(int order) {
        this.order = order;
    }
}