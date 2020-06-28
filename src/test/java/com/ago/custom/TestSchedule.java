package com.ago.custom;

import com.ago.custom.schedule.schedule.CronTaskRegistrar;
import com.ago.custom.schedule.task.ScheduleRunnable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName:TestSchedule
 * @Describe:
 * @Data:2020/6/1716:38
 * @Author:Ago
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSchedule {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;


    @Test
    public void testTask() throws InterruptedException {

        ScheduleRunnable scheduleRunnable = new ScheduleRunnable("testBean", "taskWithParams", "C10001");
        cronTaskRegistrar.addTask(scheduleRunnable, "0/3 * * * * ?");
        System.out.println(scheduleRunnable.getExpressionStr());
        Thread.sleep(3000);
    }
}
