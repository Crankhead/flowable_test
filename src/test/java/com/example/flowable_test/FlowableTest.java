package com.example.flowable_test;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class FlowableTest {

    /**
     * 流程部署
     * 当url 定义时 少了
     * 会报错   Table 'flowable1.act_ge_property' doesn't exist
     */

    @Test
    void deployFlow(){
        System.out.println("----");
        // 流程引擎配置项
        ProcessEngineConfiguration cfg = new StandaloneInMemProcessEngineConfiguration()
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable1?nullCatalogMeansCurrent=true&useUnicode=true&characterEncoding=utf8")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 获取流程引擎
        ProcessEngine processEngine = cfg.buildProcessEngine();

        // 部署流程需要获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                // 一次可部署多个
                .addClasspathResource("flow/FirstFlow.bpmn20.xml")
                .name("第一个流程图")
                .deploy();// 部署

        System.out.println(deploy.getId());
    }

    @Resource
    private RuntimeService runtimeService;

    @Test
    void startFlow(){
        // 获取  act_re_procdef表
        String processId = "FirstFlow:1:4";
        String processKey = "FirstFlow";
        // 两种方式
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);
//        runtimeService.startProcessInstanceByKey(processKey);
    }
}
