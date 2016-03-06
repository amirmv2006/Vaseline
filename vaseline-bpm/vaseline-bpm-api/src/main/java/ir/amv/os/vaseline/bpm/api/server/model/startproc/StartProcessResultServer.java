package ir.amv.os.vaseline.bpm.api.server.model.startproc;

import ir.amv.os.vaseline.bpm.api.server.model.BaseBpmResponseServer;

public class StartProcessResultServer extends BaseBpmResponseServer {

	private String processId;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
}
