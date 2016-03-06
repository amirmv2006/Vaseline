package ir.amv.os.vaseline.bpm.activiti.engine.impl.server.api.taskbeanresolver;

import org.activiti.engine.task.Task;

/**
 * Created by AMV on 3/2/2016.
 */
public interface IBaseTaskBeanResolver {

    Integer priority();

    <T> T getBeanForTask(Task task);
}
