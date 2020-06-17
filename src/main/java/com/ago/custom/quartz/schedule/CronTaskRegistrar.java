package com.ago.custom.quartz.schedule;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName:ScheduleRegisterar
 * @Describe:
 * @Data:2020/6/1715:34
 * @Author:Ago
 * @Version 1.0
 */
@Component
public class CronTaskRegistrar implements DisposableBean {

    private final Map<Runnable, ScheduledTask> scheduledTasks = Maps.newConcurrentMap();

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler(TaskScheduler taskScheduler) {
        return this.taskScheduler;
    }

    public void addTask(Runnable task, String cronExpression) {
        this.addCronTask(new CronTask(task, cronExpression));


    }

    public void removeCronTask(Runnable task) {
        ScheduledTask removedTask = this.scheduledTasks.remove(task);
        if (removedTask != null) {
            removedTask.cancel();
        }
    }

    public void addCronTask(CronTask cronTask) {
        Assert.notNull(cronTask, "cronTask is not be null");

        Runnable task = cronTask.getRunnable();

        if (this.scheduledTasks.containsKey(task)) {
            this.scheduledTasks.remove(task);
        }

        this.scheduledTasks.put(task, scheduledCronTask(cronTask));
    }


    public ScheduledTask scheduledCronTask(CronTask cronTask) {

        ScheduledTask scheduledTask = new ScheduledTask();

        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());

        return scheduledTask;
    }


    @Override
    public void destroy() throws Exception {

        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }
        System.out.println("destroy schedule");
        this.scheduledTasks.clear();
    }
}
