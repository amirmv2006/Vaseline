package ir.amv.os.vaseline.bpm.api.server.model.gotoform;

import ir.amv.os.vaseline.bpm.api.server.model.BaseBpmRequestServer;
import org.activiti.engine.task.Task;

import java.util.Map;


public class GoToTaskFormReqServer extends BaseBpmRequestServer {

	private String taskId;
	private Task task;
	private Map<String, Object> variables;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
