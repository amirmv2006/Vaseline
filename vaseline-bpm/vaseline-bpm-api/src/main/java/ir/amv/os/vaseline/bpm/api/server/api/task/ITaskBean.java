package ir.amv.os.vaseline.bpm.api.server.api.task;

import ir.amv.os.vaseline.basics.apis.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.bpm.api.server.model.compltask.CompleteTaskRequestServer;
import ir.amv.os.vaseline.bpm.api.server.model.gotoform.GoToTaskFormReqServer;
import ir.amv.os.vaseline.bpm.api.shared.model.gotoform.GoToTaskFormRespDto;
import org.activiti.engine.task.Task;

import java.util.Map;

public interface ITaskBean<GoToTaskFormResp extends GoToTaskFormRespDto, CompleteTaskReq extends CompleteTaskRequestServer> {

	String getEventName();

	Map<String, Object> getFormVariables(GoToTaskFormReqServer request) throws BaseVaselineServerException;

	Map<String, Object> complete(CompleteTaskReq request) throws BaseVaselineServerException;

	Class<GoToTaskFormResp> getGoToTaskFormRespClass();
	Class<CompleteTaskReq> getCompleteTaskReqClass();

    void setTask(Task task);
}
