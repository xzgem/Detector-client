package com.ryoua.task.config;

import com.ryoua.task.LoadInfoTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@Configuration
public class LoadInfoTaskConfig {
    @Bean
    public JobDetail resourceTask() {
        return JobBuilder.newJob(LoadInfoTask.class).withIdentity("loadInfoTask").storeDurably().build();
    }

    @Bean
    public Trigger ResourceQuartzTrigger() {
        // 3秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(resourceTask())
                .withIdentity("loadInfoTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
