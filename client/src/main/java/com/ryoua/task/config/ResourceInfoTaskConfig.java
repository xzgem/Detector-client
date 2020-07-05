package com.ryoua.task.config;

import com.ryoua.task.ResourceInfoTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/4
 **/
@Configuration
public class ResourceInfoTaskConfig {
    @Bean
    public JobDetail resourceTask() {
        return JobBuilder.newJob(ResourceInfoTask.class).withIdentity("resourceInfoTask").storeDurably().build();
    }

    @Bean
    public Trigger ResourceQuartzTrigger() {
        // 1秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(resourceTask())
                .withIdentity("resourceInfoTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
