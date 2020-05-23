package com.ryoua.controller;

import com.ryoua.acquire.BaseSystemInfoAcquire;
import com.ryoua.acquire.SystemInfoAcquire;
import com.ryoua.model.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RyouA
 * @Date: 2020/5/12 - 1:51 下午
 **/
@RestController
public class SystemController {
    @Autowired
    private BaseSystemInfoAcquire systemInfoAcquire;

    @RequestMapping(value = "/SystemInfo", method = RequestMethod.GET)
    public SystemInfo SystemInfo() {
        return systemInfoAcquire.getSystemInfo();
    }
}
