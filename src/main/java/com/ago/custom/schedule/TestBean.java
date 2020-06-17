package com.ago.custom.schedule;

import org.springframework.stereotype.Component;

/**
 * @ClassName:TestBean
 * @Describe:
 * @Data:2020/6/1716:37
 * @Author:Ago
 * @Version 1.0
 */
@Component("testBean")
public class TestBean {

    public void taskWithParams(String cronExpression,String tenantCode) {
        System.out.println("这是有参示例任务：" + tenantCode +"-"+cronExpression);
    }

    public void taskWithParams(String tenantCode) {
        System.out.println("这是有参示例任务：" + tenantCode +"-");
    }

    public void taskNoParams() {
        System.out.println("这是无参示例任务");
    }
}
