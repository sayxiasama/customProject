package com.ago.custom.schedule.task;

import com.ago.custom.redisrelation.utils.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @ClassName:SecheduleRunnable
 * @Describe:
 * @Data:2020/6/1716:07
 * @Author:Ago
 * @Version 1.0
 */
public class ScheduleRunnable implements Runnable {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleRunnable.class);

    public String beanName;

    public String methodName;

    public Object[] params;

    public ScheduleRunnable(String beanName, String methodName) {
        this.beanName = beanName;
        this.methodName = methodName;
    }

    public ScheduleRunnable(String beanName, String methodName, Object... params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    public String getExpressionStr(){
        return (String) this.params[0];
    }

    @Override
    public void run() {
        logger.info(" invoker method");

        Object targetObject = ApplicationContextHolder.getBean(beanName);

        Method method = null;

        try {
            if (params != null && params.length > 0) {

                Class[] paramCls = new Class[params.length];

                for(int i = 0 ; i < params.length ; i++){
                    paramCls[i] = params[i].getClass();
                }

                method = targetObject.getClass().getDeclaredMethod(methodName, paramCls);
            }else{
                method = targetObject.getClass().getDeclaredMethod(methodName);
            }
            ReflectionUtils.makeAccessible(method);
            if (null != params && params.length > 0) {
                method.invoke(targetObject, params);
            } else {
                method.invoke(targetObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", beanName, methodName, params), e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleRunnable that = (ScheduleRunnable) o;
        if (params == null) {
            return beanName.equals(that.beanName) &&
                    methodName.equals(that.methodName) &&
                    that.params == null;
        }

        return beanName.equals(that.beanName) &&
                methodName.equals(that.methodName) &&
                params.equals(that.params);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }

        return Objects.hash(beanName, methodName, params);
    }
}
