package com.ryoua.a.task;

import com.ryoua.a.controller.LoadController;
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
public class LoadTask extends QuartzJobBean {
    @Bean
    public JobDetail loadInfoTask() {
        return JobBuilder.newJob(LoadTask.class).withIdentity("loadInfoTask").storeDurably().build();
    }

    @Bean
    public Trigger LoadQuartzTrigger() {
        // 3秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(loadInfoTask())
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
