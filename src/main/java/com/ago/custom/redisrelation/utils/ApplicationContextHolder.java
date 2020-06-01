package com.ago.custom.redisrelation.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static Object getBean(Class clazzType) {
        Object bean = applicationContext.getBean(clazzType);
        return bean;
    }


    public static Object getBean(String beanName, Class clazzType) {
        Object bean = applicationContext.getBean(beanName, clazzType);
        return bean;
    }


}
