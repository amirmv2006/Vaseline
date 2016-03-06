package ir.amv.os.vaseline.bpm.api.server.model.compltask;

import ir.amv.os.vaseline.bpm.api.server.model.BaseBpmRequestServer;

import java.util.Map;


public class CompleteTaskRequestServer extends BaseBpmRequestServer {

	private String description;
	private String taskId;
	private Map<String, Object> variables;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}
