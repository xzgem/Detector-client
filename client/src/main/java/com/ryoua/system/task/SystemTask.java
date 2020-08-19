package com.ryoua.system.task;

import com.ryoua.system.controller.LoadController;
import com.ryoua.system.controller.SystemController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/19
 **/
@Configuration
public class SystemTask extends QuartzJobBean {
    @Bean
    public JobDetail systemSendTask() {
        return JobBuilder.newJob(LoadTask.class).withIdentity("systemTask").storeDurably().build();
    }

    @Bean
    public Trigger SystemQuartzTrigger() {
        // 3秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(systemSendTask())
                .withIdentity("systemTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Autowired
    private SystemController systemController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        systemController.sendLoadInfo();
    }
}
