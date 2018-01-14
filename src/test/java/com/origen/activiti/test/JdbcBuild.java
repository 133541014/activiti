package com.origen.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * @author:WangYichao
 * @Description:数据库建表测试
 * @Date:Created in 2018/1/7 21:59
 */
public class JdbcBuild {

    /**
     * @Description:创建数据库表
     * @Author:WangYichao
     * @Date:2018/1/7 22:23
     */
    @Test
    public void jdbcBuild(){

      /*  //创建流程引擎配置对象
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        //设置配置参数
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");

        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&charset=utf8");

        processEngineConfiguration.setJdbcUsername("root");

        processEngineConfiguration.setJdbcPassword("");

        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        //创建流程引擎对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

        System.out.print("ProcessEngine: "+processEngine);*/

        // 引擎配置
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine=pec.buildProcessEngine();

        System.out.print("构建数据库表结构成功");

    }
}
