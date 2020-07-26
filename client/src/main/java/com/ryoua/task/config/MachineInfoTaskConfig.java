package com.ryoua.task.config;/*
 * @Author: RyouA
 * @Date: 2020/5/23 - 21:46
 */

import com.ryoua.task.MachineInfoTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MachineInfoTaskConfig {
    @Bean
    public JobDetail systemTask() {
        return JobBuilder.newJob(MachineInfoTask.class).withIdentity("machineInfoTask").storeDurably().build();
    }

//    @Bean
//    public Trigger SystemQuartzTrigger() {
//        // 1小时执行一次
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInHours(1)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(systemTask())
//                .withIdentity("machineInfoTask")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
}
