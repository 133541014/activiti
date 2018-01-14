package com.origen.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author:WangYichao
 * @Description:流程部署执行测试
 * @Date:Created in 2018/1/12 21:52
 */
public class HelloWorld {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * @Description:部署流程定义
     * @Author:WangYichao
     * @Date:2018/1/12 22:14
     */
    @Test
    public void deploymentProcessDifinition() {
        //从classpath下获取文件输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("helloworld入门程序")
                .addZipInputStream(zipInputStream)
                .deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    /**
     * @Description:启动流程实例
     * @Author:WangYichao
     * @Date:2018/1/12 22:30
     */
    @Test
    public void startProcessInstance() {
        ProcessInstance instance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("HelloWorld");
        System.out.println("实例id:" + instance.getId());
        System.out.println("流程定义id:" + instance.getProcessDefinitionId());
    }

    /**
     * @Description:查询当前人个人任务
     * @Author:WangYichao
     * @Date:2018/1/13 9:32
     */
    @Test
    public void findUserTask() {
        String assignee = "李四?";
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        if(tasks.size()==0){
            System.out.println("没有任务需要执行");
            return;
        }
        for (Task task : tasks) {
            System.out.println("任务id:"+task.getId());
            System.out.println("任务名称:"+task.getName());
            System.out.println("任务创建时间:"+task.getCreateTime());
            System.out.println("任务办理人:"+task.getAssignee());
            System.out.println("流程实例id:"+task.getProcessInstanceId());
            System.out.println("流程定义id:"+task.getProcessDefinitionId());
        }
    }

    /**
     * @Description:完成任务
     * @Author:WangYichao
     * @Date:2018/1/13 9:49
     */
    @Test
    public void completeTask(){
        String taskId = "2505";
        processEngine.getTaskService()
                     .complete(taskId);
        System.out.println("完成任务，任务id:"+taskId);
    }
}