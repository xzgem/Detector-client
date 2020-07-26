package com.ryoua.task;

import com.ryoua.controller.SystemController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @Author: RyouA
 * @Date: 2020/5/23 - 8:29 下午
 **/
@Component
public class MachineInfoTask extends QuartzJobBean {
    @Autowired
    private SystemController systemController;

    @Override
    public void executeInternal(JobExecutionContext context) {
        systemController.SystemInfo();
    }
}
