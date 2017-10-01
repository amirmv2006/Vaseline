package ir.amv.os.vaseline.bpm.api.shared.model;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.baseimpl.BaseEmptyDtoImpl;

public class BaseBpmResponseDto extends BaseEmptyDtoImpl {

	private static final long serialVersionUID = 1L;

	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
