package ir.amv.os.vaseline.bpm.activiti.engine.impl.server.api.taskbeanresolver.defaultresolver;

import ir.amv.os.vaseline.bpm.activiti.engine.impl.server.api.taskbeanresolver.IBaseTaskBeanResolver;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by AMV on 3/2/2016.
 */
@Component
public class DefaultTaskBeanResolver
        implements IBaseTaskBeanResolver, ApplicationContextAware {

    private static final Integer PRIORITY = 100;
    private ApplicationContext applicationContext;

    @Override
    public Integer priority() {
        return PRIORITY;
    }

    @Override
    public <T> T getBeanForTask(Task task) {
        String taskDefinitionKey = task.getTaskDefinitionKey();
        Object bean = applicationContext.getBean(taskDefinitionKey);
        return (T) bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
