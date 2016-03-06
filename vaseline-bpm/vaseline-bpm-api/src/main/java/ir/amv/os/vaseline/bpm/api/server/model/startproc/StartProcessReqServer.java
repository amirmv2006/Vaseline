package ir.amv.os.vaseline.bpm.api.server.model.startproc;

import ir.amv.os.vaseline.bpm.api.server.model.BaseBpmRequestServer;

import java.util.Map;

public class StartProcessReqServer extends BaseBpmRequestServer {

	private String processDefinitionKey;
	private Map<String, Object> variablesMap;

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public Map<String, Object> getVariablesMap() {
		return variablesMap;
	}

	public void setVariablesMap(Map<String, Object> variablesMap) {
		this.variablesMap = variablesMap;
	}
}
