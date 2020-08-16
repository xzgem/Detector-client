package com.ryoua.system.task;

import com.ryoua.system.controller.TrafficController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/16
 **/
@Configuration
public class TrafficTask extends QuartzJobBean {
    @Bean
    public JobDetail trafficInfoTask() {
        return JobBuilder.newJob(TrafficTask.class).withIdentity("trafficTask").storeDurably().build();
    }

    @Bean
    public Trigger TrafficQuartzTrigger() {
        // 3秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(trafficInfoTask())
                .withIdentity("trafficTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Autowired
    private TrafficController trafficController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        trafficController.sendTrafficInfo();
    }
}
