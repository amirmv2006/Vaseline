package ir.amv.os.vaseline.bpm.api.shared.model.startproc;

import ir.amv.os.vaseline.bpm.api.shared.model.BaseBpmResponseDto;

public class StartProcessResultDto extends BaseBpmResponseDto {

	private static final long serialVersionUID = 1L;
	
	private String processId;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
}
