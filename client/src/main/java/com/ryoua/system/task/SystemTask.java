package com.ryoua.system.task;

import com.ryoua.system.controller.SystemController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Configuration
public class SystemTask extends QuartzJobBean {
    @Bean
    public JobDetail systemTask() {
        return JobBuilder.newJob(SystemTask.class).withIdentity("systemInfoTask").storeDurably().build();
    }

    @Bean
    public Trigger SystemQuartzTrigger() {
        // 1小时执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(systemTask())
                .withIdentity("systemInfoTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Autowired
    private SystemController systemController;

    @Override
    public void executeInternal(JobExecutionContext context) {
        systemController.sendSystemInfo();
    }
}
