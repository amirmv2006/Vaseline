package ir.amv.os.vaseline.bpm.api.shared.model.gotoform;

import ir.amv.os.vaseline.bpm.api.shared.model.BaseBpmResponseDto;

import java.util.Map;

public class GoToTaskFormRespDto extends BaseBpmResponseDto {

	private static final long serialVersionUID = 1L;
	
	private String eventName;
	private Map<String, Object> variables;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

}
