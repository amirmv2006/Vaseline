package ir.amv.os.vaseline.bpm.activiti.engine.impl.server.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.bpm.activiti.engine.impl.server.api.taskbeanresolver.IBaseTaskBeanResolver;
import ir.amv.os.vaseline.bpm.api.server.api.IVaselineBpmApi;
import ir.amv.os.vaseline.bpm.api.server.api.task.ITaskBean;
import ir.amv.os.vaseline.bpm.api.server.model.compltask.CompleteTaskRequestServer;
import ir.amv.os.vaseline.bpm.api.server.model.compltask.CompleteTaskResponseServer;
import ir.amv.os.vaseline.bpm.api.server.model.gotoform.GoToTaskFormReqServer;
import ir.amv.os.vaseline.bpm.api.server.model.gotoform.GoToTaskFormRespServer;
import ir.amv.os.vaseline.bpm.api.server.model.startproc.StartProcessReqServer;
import ir.amv.os.vaseline.bpm.api.server.model.startproc.StartProcessResultServer;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by AMV on 3/2/2016.
 */
@Component
public class VaselineBpmApiImpl
        extends BaseApiImpl
        implements IVaselineBpmApi {

    private RuntimeService runtimeService;
    private TaskService taskService;
    private RepositoryService repositoryService;
    private IAuthenticationApi authenticationAPI;
    private List<IBaseTaskBeanResolver> taskBeanResolvers;

    protected Map<String, Object> getVariables(String processInstanceId) {
        return runtimeService.getVariables(processInstanceId);
    }

    @Override
    public void setVariable(String taskId, String variableName, Serializable value) throws BaseVaselineServerException {
        Task taskById = getTaskById(taskId);
        runtimeService.setVariable(taskById.getExecutionId(), variableName, value);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getBean(Task task) {
        for (IBaseTaskBeanResolver taskBeanResolver : taskBeanResolvers) {
            T bean = taskBeanResolver.getBeanForTask(task);
            if (bean != null) {
                return bean;
            }
        }
        return null;
    }

//    @Override
//    @Transactional
//    public DeployResourceResponseServer deployResources(
//            DeployResourceRequestServer deployRequest) {
//        DeploymentQuery deploymentQuery = repositoryService
//                .createDeploymentQuery()
//                .deploymentName(deployRequest.getName());
//        if (deploymentQuery.count() == 0) {
//            DeploymentBuilder deploymentBuilder = repositoryService
//                    .createDeployment().name(deployRequest.getName())
//                    .addClasspathResource(deployRequest.getClasspathResource());
//            Deployment deploy = deploymentBuilder.deploy();
//            DeployResourceResponseServer responseServer = new DeployResourceResponseServer();
//            responseServer.setDeployId(deploy.getId());
//            return responseServer;
//        } else {
//            Deployment singleResult = deploymentQuery.singleResult();
//            DeployResourceResponseServer responseServer = new DeployResourceResponseServer();
//            responseServer.setDeployId(singleResult.getId());
//            return responseServer;
//        }
//    }

    @Override
    @Transactional
    public StartProcessResultServer startProcess(StartProcessReqServer req)
            throws BaseVaselineServerException {
        Map<String, Object> variablesMap = req.getVariablesMap();
        if (variablesMap == null) {
            variablesMap = new HashMap<String, Object>();
        }
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(req.getProcessDefinitionKey(),
                        variablesMap);
        StartProcessResultServer response = new StartProcessResultServer();
        response.setProcessId(processInstance.getId());
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(authenticationAPI.getCurrentUsername())
                .processInstanceId(processInstance.getId()).list();
        if (!list.isEmpty()) {
            response.setTaskId(list.get(0).getId());
        }
        return response;
    }

    @Override
    @Transactional
    public GoToTaskFormRespServer goToTaskForm(GoToTaskFormReqServer req)
            throws BaseVaselineServerException {
        Task singleResult = getTaskById(req.getTaskId());
        Map<String, Object> reqVariables = req.getVariables();
        Map<String, Object> variables = runtimeService
                .getVariables(singleResult.getExecutionId());
        if (reqVariables == null) {
            reqVariables = new HashMap<String, Object>();
        }
        if (variables != null) {
            reqVariables.putAll(variables);
        }
        req.setVariables(reqVariables);
        ITaskBean<?, ?> bean = getBean(singleResult);
        bean.setTask(singleResult);
        GoToTaskFormRespServer result = new GoToTaskFormRespServer();
        result.setEventName(bean.getEventName());
        result.setTaskId(singleResult.getId());
        result.setVariables(bean.getFormVariables(req));
        result.setResponseClass(bean.getGoToTaskFormRespClass());
        return result;
    }

    public Task getTaskById(String taskId) {
        TaskQuery taskQuery = taskService.createTaskQuery().taskId(taskId);
        Task singleResult = taskQuery.singleResult();
        return singleResult;
    }

    @Override
    public <TaskBean extends ITaskBean<?, ?>> TaskBean getTaskBean(String taskId) {
        Task taskById = getTaskById(taskId);
        ITaskBean<?, ?> bean = getBean(taskById);
        bean.setTask(taskById);
        return (TaskBean) bean;
    }

    @Override
    public List<String> getDecisionsForTask(String taskId) {
        Task taskById = getTaskById(taskId);
        BpmnModel bpmnModel = repositoryService.getBpmnModel(taskById.getProcessDefinitionId());
        UserTask userTask = (UserTask) bpmnModel.getFlowElement(taskById.getTaskDefinitionKey());
        String targetRef = userTask.getOutgoingFlows().get(0).getTargetRef();
        ExclusiveGateway exclusiveGateway = (ExclusiveGateway) bpmnModel.getFlowElement(targetRef);
        List<SequenceFlow> outgoingFlows = exclusiveGateway.getOutgoingFlows();
        List<String> result = new ArrayList<String>();
        for (SequenceFlow outgoingFlow : outgoingFlows) {
            result.add(outgoingFlow.getName());
        }
        return result;
    }

    @Override
    @Transactional
    public boolean claim(String taskId) throws BaseVaselineServerException {
        try {
            taskService.claim(taskId, authenticationAPI.getCurrentUsername());
            return true;
        } catch (ActivitiTaskAlreadyClaimedException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public CompleteTaskResponseServer completeTask(CompleteTaskRequestServer req) {
        Task task = getTaskById(req.getTaskId());

        Map<String, Object> reqVariables = req.getVariables();
        Map<String, Object> variables = runtimeService.getVariables(task
                .getExecutionId());
        if (reqVariables == null) {
            reqVariables = new HashMap<String, Object>();
        }
        if (variables == null) {
            variables = new HashMap<String, Object>();
        }
        variables.putAll(reqVariables);
        req.setVariables(variables);

        ITaskBean<?, CompleteTaskRequestServer> bean = getBean(task);
        try {
            Map<String, Object> complete = bean.complete(req);
            taskService.complete(req.getTaskId(), complete);
            CompleteTaskResponseServer result = new CompleteTaskResponseServer();
            return result;
        } catch (BaseVaselineServerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public InputStream getProcessImage(String taskId, boolean showAllTasks)
            throws BaseVaselineServerException {
        Task taskById = getTaskById(taskId);
//		BpmnModel bpmnModel = ((RepositoryServiceImpl) repositoryService)
//				.getBpmnModel(taskById.getProcessDefinitionId());
//		List<String> highLightedActivities = showAllTasks ? runtimeService
//				.getActiveActivityIds(taskById.getProcessInstanceId()) : Arrays
//				.asList(taskId);
        InputStream diagramStream = repositoryService.getProcessDiagram(taskById.getProcessDefinitionId());
//		InputStream diagramStream = ProcessDiagramGenerator.generateDiagram(
//				bpmnModel, "png", highLightedActivities);
        return diagramStream;
    }

    private TaskQuery personalTaskQuery() throws BaseVaselineServerException {
        String currentUsername = authenticationAPI.getCurrentUsername();
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(
                currentUsername);
        return taskQuery;
    }

    @Override
    @Transactional
    public List<Task> getPersonalTaskList(PagingDto paginationDTO)
            throws BaseVaselineServerException {
        TaskQuery taskQuery = personalTaskQuery();
        List<Task> listPage = taskQuery.listPage(paginationDTO.getPageNumber()
                * paginationDTO.getPageSize(), paginationDTO.getPageSize());
        return listPage;
    }

    @Override
    @Transactional
    public Long countPersonalTaskList() throws BaseVaselineServerException {
        TaskQuery taskQuery = personalTaskQuery();
        long count = taskQuery.count();
        return count;
    }

    private TaskQuery candidateUserTaskQuery() throws BaseVaselineServerException {
        String currentUsername = authenticationAPI.getCurrentUsername();
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateUser(
                currentUsername);
        return taskQuery;
    }

    @Override
    @Transactional
    public List<Task> getCandidateUserTaskList(PagingDto paginationDTO)
            throws BaseVaselineServerException {
        TaskQuery taskQuery = candidateUserTaskQuery();
        List<Task> listPage = taskQuery.listPage(paginationDTO.getPageNumber()
                * paginationDTO.getPageSize(), paginationDTO.getPageSize());
        return listPage;
    }

    @Override
    @Transactional
    public Long countCandidateUserTaskList() throws BaseVaselineServerException {
        TaskQuery taskQuery = candidateUserTaskQuery();
        long count = taskQuery.count();
        return count;
    }

    @Autowired
    public void setAuthenticationAPI(IAuthenticationApi authenticationAPI) {
        this.authenticationAPI = authenticationAPI;
    }

    @Autowired
    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setTaskBeanResolvers(List<IBaseTaskBeanResolver> taskBeanResolvers) {
        this.taskBeanResolvers = taskBeanResolvers;
        Collections.sort(taskBeanResolvers, new Comparator<IBaseTaskBeanResolver>() {
            @Override
            public int compare(IBaseTaskBeanResolver o1, IBaseTaskBeanResolver o2) {
                return o2.priority().compareTo(o1.priority()); // sort descending
            }
        });
    }
}
