package com.ago.custom.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @ClassName:ScheduleConfig
 * @Describe:
 * @Data:2020/6/1715:27
 * @Author:Ago
 * @Version 1.0
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public TaskScheduler taskSchedule(){

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

        taskScheduler.setPoolSize(5);

        taskScheduler.setRemoveOnCancelPolicy(true);

        taskScheduler.setThreadNamePrefix("schedule-task-");

        return taskScheduler;
    }
}
