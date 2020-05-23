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
    public JobDetail task() {
        return JobBuilder.newJob(SystemInfoTask.class).withIdentity("systemInfoTask").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        //5秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(task())
                .withIdentity("systemInfoTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
