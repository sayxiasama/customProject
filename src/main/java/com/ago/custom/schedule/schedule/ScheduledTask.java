package com.ago.custom.schedule.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName:ScheduleTask
 * @Describe:
 * @Data:2020/6/1715:43
 * @Author:Ago
 * @Version 1.0
 */
public final class ScheduledTask {

    public volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
