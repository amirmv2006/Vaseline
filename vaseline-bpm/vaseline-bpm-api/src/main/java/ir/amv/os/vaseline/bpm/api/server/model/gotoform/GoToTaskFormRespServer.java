package ir.amv.os.vaseline.bpm.api.server.model.gotoform;

import ir.amv.os.vaseline.bpm.api.server.model.BaseBpmResponseServer;

import java.util.Map;

public class GoToTaskFormRespServer extends BaseBpmResponseServer {

	private String eventName;
	private Map<String, Object> variables;
	private Class<?> responseClass;

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Class<?> getResponseClass() {
		return responseClass;
	}

	public void setResponseClass(Class<?> responseClass) {
		this.responseClass = responseClass;
	}

}
