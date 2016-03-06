package ir.amv.os.vaseline.bpm.api.shared.model.startproc;

import ir.amv.os.vaseline.bpm.api.shared.model.BaseBpmRequestDto;

import java.util.Map;

public class StartProcessReqDto extends BaseBpmRequestDto {

	private static final long serialVersionUID = 1L;
	
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
