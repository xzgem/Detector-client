package com.ryoua.task;

import com.alibaba.fastjson.JSON;
import com.ryoua.controller.SystemController;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @Author: RyouA
 * @Date: 2020/5/23 - 8:29 下午
 **/
@Component
@Slf4j
public class SystemInfoTask extends QuartzJobBean {
    @Autowired
    private SystemController systemController;

    @Override
    public void executeInternal(JobExecutionContext context) {
        systemController.SystemInfo();
    }
}
