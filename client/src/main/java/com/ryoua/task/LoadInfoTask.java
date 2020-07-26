package com.ryoua.task;

import com.ryoua.controller.LoadController;
import com.ryoua.controller.SystemController;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
public class LoadInfoTask extends QuartzJobBean {
    @Autowired
    private LoadController LoadController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LoadController.getLoadInfo();
    }
}
