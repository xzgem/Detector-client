package com.ryoua.task;

import com.ryoua.controller.SystemController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/*
 * @Author: RyouA
 * @Date: 2020/5/23 - 21:46
 */
@Configuration
public class SystemInfoTask extends QuartzJobBean {
    @Bean
    public JobDetail systemTask() {
        return JobBuilder.newJob(SystemInfoTask.class).withIdentity("machineInfoTask").storeDurably().build();
    }

    @Bean
    public Trigger SystemQuartzTrigger() {
        // 1小时执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(systemTask())
                .withIdentity("machineInfoTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Autowired
    private SystemController systemController;

    @Override
    public void executeInternal(JobExecutionContext context) {
        systemController.SystemInfo();
    }
}
