package ir.amv.os.vaseline.bpm.api.server.api;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.bpm.api.server.api.task.ITaskBean;
import ir.amv.os.vaseline.bpm.api.server.model.compltask.CompleteTaskRequestServer;
import ir.amv.os.vaseline.bpm.api.server.model.compltask.CompleteTaskResponseServer;
import ir.amv.os.vaseline.bpm.api.server.model.gotoform.GoToTaskFormReqServer;
import ir.amv.os.vaseline.bpm.api.server.model.gotoform.GoToTaskFormRespServer;
import ir.amv.os.vaseline.bpm.api.server.model.startproc.StartProcessReqServer;
import ir.amv.os.vaseline.bpm.api.server.model.startproc.StartProcessResultServer;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 3/2/2016.
 */
public interface IVaselineBpmApi
        extends IBaseApi {

//    DeployResourceResponseServer deployResources(DeployResourceRequestServer deployRequest) throws BaseBusinessException;

    StartProcessResultServer startProcess(StartProcessReqServer req) throws BaseVaselineServerException;
    GoToTaskFormRespServer goToTaskForm(GoToTaskFormReqServer req) throws BaseVaselineServerException;
    CompleteTaskResponseServer completeTask(CompleteTaskRequestServer req) throws BaseVaselineServerException;

    InputStream getProcessImage(String taskId, boolean showAllTasks) throws BaseVaselineServerException;

    boolean claim(String taskId) throws BaseVaselineServerException;
    void setVariable(String taskId, String variableName, Serializable value) throws BaseVaselineServerException;
    <TaskBean extends ITaskBean<?, ?>> TaskBean getTaskBean(String taskId);

    List<String> getDecisionsForTask(String taskId);

    // Cartable Start
    List<Task> getPersonalTaskList(PagingDto paginationDTO) throws BaseVaselineServerException;
    Long countPersonalTaskList() throws BaseVaselineServerException	;
    List<Task> getCandidateUserTaskList(PagingDto paginationDTO) throws BaseVaselineServerException;

    Long countCandidateUserTaskList() throws BaseVaselineServerException	;
    // Cartable End

}
