package com.ago.custom.zframeextends;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName:ApplicationContextCustom
 * @Describe:
 * @Data:2020/6/815:22
 * @Author:Ago
 * @Version 1.0
 */
public class ApplicationContextCustom implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("bean总数:" + configurableApplicationContext.getBeanDefinitionCount());

        for (String beanDefinitionName : configurableApplicationContext.getBeanDefinitionNames()) {
            System.out.println("bean 名称为" + beanDefinitionName);
        }
    }
}
