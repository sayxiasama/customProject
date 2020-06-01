package com.ago.custom.business.demo.asyncconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName:AsyncConfig
 * @Describe:
 * @Data:2020/5/2616:43
 * @Author:Ago
 * @Version 1.0
 */
@Configuration
public class AsyncConfig {

    private static final int CORE_POOL_SIZE = 6;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;

    @Bean
    public Executor taskExecutor(){
        //Spring默认核心线程数量为1 , 最大线程容量 与 线程队列容量不受限制
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //最大核心线程
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        //队列最大容量
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        //最大线程数已满时,此策略不会丢失请求,但是会影响程序性能
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //设置线程名称
        threadPoolTaskExecutor.setThreadNamePrefix("my customThreadPoolTaskExecutor");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @PostConstruct
    public void init(){
        System.out.println("taskExecutor set finish");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("taskExecutor destroy");
    }
}
