package ir.amv.os.vaseline.bpm.api.shared.model.gotoform;

import ir.amv.os.vaseline.bpm.api.shared.model.BaseBpmRequestDto;

public class GoToTaskFormReqDto extends BaseBpmRequestDto {

	private static final long serialVersionUID = 1L;
	
	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
