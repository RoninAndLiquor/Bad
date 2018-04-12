package high.controller;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.pvm.ProcessDefinitionBuilder;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activity")
public class ActivityController {
	
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping("first")
	public List<String> firstDemo(){
		List<String> result = new ArrayList<String>();
		//根据bpmn文件部署流程
		Deployment deploy = repositoryService.
				createDeployment().
				addClasspathResource("MyProcess.bpmn").
				deploy();
		//获取流程定义
		ProcessDefinition singleResult = repositoryService.
				createProcessDefinitionQuery().
				deploymentId(deploy.getId()).
				singleResult();
		//启动流程定义 返回流程实例
		/*ProcessInstance startProcessInstanceByKey = runtimeService.startProcessInstanceByKey("myProcess");*/
		ProcessInstance startProcessInstanceById = runtimeService.
				startProcessInstanceById(singleResult.getId());
		String processId = startProcessInstanceById.getId();
		result.add("流程创建成功，当前流程实例ID："+processId);
		Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		result.add("第一次执行任务，任务名称："+task.getName());
		taskService.complete(task.getId());
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		result.add("第二次执行任务，任务名称"+task.getName());
		taskService.complete(task.getId());
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		result.add("task为null，任务执行完毕"+task);
		return result;
	}

}
