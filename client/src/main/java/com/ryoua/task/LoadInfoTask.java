package com.ryoua.task;

import com.ryoua.controller.LoadController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@Configuration
public class LoadInfoTask extends QuartzJobBean {
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

    @Autowired
    private LoadController LoadController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LoadController.sendLoadInfo();
    }
}
