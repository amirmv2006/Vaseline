package ir.amv.os.vaseline.bpm.api.shared.model.compltask;

import ir.amv.os.vaseline.bpm.api.shared.model.BaseBpmRequestDto;

import java.util.Map;

public class CompleteTaskRequestDto extends BaseBpmRequestDto {

	private static final long serialVersionUID = 1L;
	
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
