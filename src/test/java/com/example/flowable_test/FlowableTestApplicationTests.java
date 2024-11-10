package com.example.flowable_test;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FlowableTestApplicationTests {

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private RepositoryService repositoryService;

    @Test
    void contextLoads() {
        System.out.println(processEngine);
        Deployment deploy = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("flow/FirstFlow.bpmn20.xml")
                .name("第一个流程图")
                .deploy();// 部署

        System.out.println(deploy.getId());


    }

}
