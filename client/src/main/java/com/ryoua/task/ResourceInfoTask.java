package com.ryoua.task;

import com.ryoua.controller.SystemController;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/4
 **/
@Component
public class ResourceInfoTask extends QuartzJobBean {
    @Autowired
    private SystemController systemController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        systemController.ResourceInfo();
    }
}
