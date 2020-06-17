package com.ago.custom.redisrelation.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.stereotype.Component;

/**
 * @ClassName:ApplicationContextHolder
 * @Describe:
 * @Data:2020/6/114:39
 * @Author:Ago
 * @Version 1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    @Autowired
    private static  ApplicationContext applicationContext;

    private static ApplicationContextHolder instance = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ApplicationContextHolder() {

    }

//    public static ApplicationContextHolder getInstance() {
//        if (instance == null) {
//            synchronized (ApplicationContextHolder.class) {
//                if (instance == null) {
//                    return new ApplicationContextHolder();
//                }
//            }
//        }
//        return instance;
//    }

    public static Object getBean(Class clazzType) {
        Object bean = applicationContext.getBean(clazzType);
        return bean;
    }


    public static Object getBean(String beanName, Class clazzType) {
        Object bean = applicationContext.getBean(beanName, clazzType);
        return bean;
    }

    public static Object getBean(String beanName){
        Object bean = applicationContext.getBean(beanName);
        return bean;
    }

}
