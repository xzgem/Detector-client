package com.ryoua.task.config;/*
 * @Author: RyouA
 * @Date: 2020/5/23 - 21:46
 */

import com.ryoua.task.SystemInfoTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemInfoTaskConfig {
    @Bean
    public JobDetail systemTask() {
        return JobBuilder.newJob(SystemInfoTask.class).withIdentity("systemInfoTask").storeDurably().build();
    }

    @Bean
    public Trigger SystemQuartzTrigger() {
        // 一小时执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(systemTask())
                .withIdentity("systemInfoTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
