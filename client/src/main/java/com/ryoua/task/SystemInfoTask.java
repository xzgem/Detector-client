package com.ryoua.task;

import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: RyouA
 * @Date: 2020/5/23 - 8:29 下午
 **/
public class SystemInfoTask implements Job {
    @Autowired
    private Logger logger;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("这里是你的定时任务: " + JSON.toJSONString(jobExecutionContext.getJobDetail()));
    }

    public void SystemInfoAcquireTask() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

        }
    }
}
